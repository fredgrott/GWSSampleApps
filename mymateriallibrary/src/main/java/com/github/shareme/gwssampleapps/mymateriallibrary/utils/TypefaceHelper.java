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
package com.github.shareme.gwssampleapps.mymateriallibrary.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.util.SimpleArrayMap;

/**
 * Each call to Typeface.createFromAsset will load a new instance of the typeface into memory,
 * and this memory is not consistently get garbage collected
 * http://code.google.com/p/android/issues/detail?id=9904
 * (It states released but even on Lollipop you can see the typefaces accumulate even after
 * multiple GC passes)
 *
 * You can detect this by running:
 * adb shell dumpsys meminfo com.your.packagenage
 *
 * You will see output like:
 *
 * Asset Allocations
 * zip:/data/app/com.your.packagenage-1.apk:/assets/Roboto-Medium.ttf: 125K
 * zip:/data/app/com.your.packagenage-1.apk:/assets/Roboto-Medium.ttf: 125K
 * zip:/data/app/com.your.packagenage-1.apk:/assets/Roboto-Medium.ttf: 125K
 * zip:/data/app/com.your.packagenage-1.apk:/assets/Roboto-Regular.ttf: 123K
 * zip:/data/app/com.your.packagenage-1.apk:/assets/Roboto-Medium.ttf: 125K
 * Created by fgrott on 7/22/2016.
 */
@SuppressWarnings("unused")
public class TypefaceHelper {

  private static final SimpleArrayMap<String, Typeface> cache = new SimpleArrayMap<>();

  public static Typeface get(Context c, String name) {
    synchronized (cache) {
      if (!cache.containsKey(name)) {
        try {
          Typeface t = Typeface.createFromAsset(
                  c.getAssets(), String.format("fonts/%s", name));
          cache.put(name, t);
          return t;
        } catch (RuntimeException e) {
          return null;
        }
      }
      return cache.get(name);
    }
  }

}
