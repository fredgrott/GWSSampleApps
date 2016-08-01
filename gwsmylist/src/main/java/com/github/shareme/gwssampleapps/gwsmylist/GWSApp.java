/*
   Copyright (C) 2016 Fred Grott(aka shareme GrottWorkShop)

Licensed under the Apache License, Version 2.0 (the "License"); you
may not use this file except in compliance with the License. You may
obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an
"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
either express or implied. See the License for the specific language
governing permissions and limitations under License.
 */
package com.github.shareme.gwssampleapps.gwsmylist;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;

import com.github.shareme.gwssampleapps.anrwatchdog.ANRWatchDog;
import com.nshmura.strictmodenotifier.StrictModeNotifier;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import timber.log.Timber;

/**
 * Just the basic boilerplate for an Android Application implementing
 * Timber log wrapper, LeakCanary RefWatcher to catch memory leaks of
 * activities and fragments,
 *
 * Usually, we only want methods that fast return as initialises in
 * the application class.
 *
 * Created by fgrott on 7/22/2016.
 */
@SuppressWarnings("unused")
public class GWSApp extends Application {

  private RefWatcher refWatcher;

  ANRWatchDog anrWatchDog = new ANRWatchDog(2000);


  /**
   * onCreate method for our extended Application class features:
   *
   *    initialization of our Timber Log Wrapper
   *    initializations of leakcanary to catch memory leaks of activities/fragments
   *    initialization of a top uncaught exception handler
   *    initialization of an ANR Watcher to watch and log anrs
   *    initialization and setup of strictmode
   */
  @Override
  public void onCreate() {
    super.onCreate();

    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
      refWatcher = LeakCanary.install(this);

      StrictModeNotifier.install(this);

      //setup StrictMode.
      //
      // penaltyLog() should be called for strictmode-notifier
      //
      new Handler().post(() -> {
        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .permitDiskReads()
                .permitDiskWrites()
                .penaltyLog() // Must!
                .build();
        StrictMode.setThreadPolicy(threadPolicy);

        StrictMode.VmPolicy vmPolicy = new StrictMode.VmPolicy.Builder()
                .detectAll()
                .penaltyLog() // Must!
                .build();
        StrictMode.setVmPolicy(vmPolicy);
      });

    } else {
      Timber.plant(new CrashReportingTree());
    }

    final Thread.UncaughtExceptionHandler oldHandler = Thread.getDefaultUncaughtExceptionHandler();
    Thread.setDefaultUncaughtExceptionHandler((paramThread, paramThrowable) -> {
      //own error handling here

      if(oldHandler != null)
        oldHandler.uncaughtException(
                paramThread,
                paramThrowable
        ); //delegates to Android's error handling
      else
        System.exit(2);//prevents the service/app from freezing
    });

    anrWatchDog.setANRListener(error -> {
      Timber.e("ANR-Watchdog", "Detected Application Not Responding!");

      // Some tools like ACRA are serializing the exception, so we must make sure the exception serializes correctly
      try {
        new ObjectOutputStream(new ByteArrayOutputStream()).writeObject(error);
      }
      catch (IOException ex) {
        throw new RuntimeException(ex);
      }

      Timber.i("ANR-Watchdog", "Error was successfully serialized");

      throw error;
    });

    anrWatchDog.start();


  }

  /**
   * A tree which logs important information for crash reporting.
   */
  private static class CrashReportingTree extends Timber.Tree {
    @Override
    protected void log(int priority, String tag, String message, Throwable t) {
      if (priority == Log.VERBOSE || priority == Log.DEBUG) {
        return;
      }

      GWSCrashLibrary.log(priority, tag, message);

      if (t != null) {
        if (priority == Log.ERROR) {
          GWSCrashLibrary.logError(t);
        } else if (priority == Log.WARN) {
          GWSCrashLibrary.logWarning(t);
        }
      }
    }
  }

  /**
   * RefWatcher for leakcanary integration
   * @param context the context
   * @return the refWatcher for the application
   */
  public static RefWatcher getRefWatcher(Context context){
    GWSApp application = (GWSApp)context.getApplicationContext();
    return application.refWatcher;
  }


}
