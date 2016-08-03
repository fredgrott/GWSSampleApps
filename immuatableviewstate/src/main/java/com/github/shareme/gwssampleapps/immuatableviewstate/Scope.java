/*
 The MIT License (MIT)

Copyright (c) 2015 Konstantin Mikheev sirstripy-at-gmail-com
Modifications Copyright(C) 2016 Fred Grott(GrottWorkShop)

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

 */
package com.github.shareme.gwssampleapps.immuatableviewstate;

import android.os.Bundle;

import java.util.UUID;

/**
 * Represents an easy and type-safe access to {@link Immutable}.
 * <p>
 * {@link Scope} is an interface to access internal state of a View,
 * including its background tasks and callbacks.
 * Created by fgrott on 8/3/2016.
 */
@SuppressWarnings("unused")
public class Scope<S extends ViewState> {

  private final String key;
  private final Immutable immutable = Immutable.INSTANCE;

  /**
   * Constructs a new scope from a given initial state.
   */
  public Scope(S state) {
    this.key = UUID.randomUUID().toString();
    immutable.create(key, state);
  }

  /**
   * Restores a scope from a given {@link Bundle}.
   */
  public Scope(Bundle bundle) {
    this.key = bundle.getString("key");
    immutable.restore(key, bundle.getParcelable("scope"));
  }

  /**
   * Returns the current scope's state.
   */
  @SuppressWarnings("unchecked")
  public S state() {
    return (S) Immutable.INSTANCE.state(key);
  }

  /**
   * Saves the scope into {@link Bundle}.
   */
  public Bundle save() {
    Bundle bundle = new Bundle();
    bundle.putString("key", key);
    bundle.putParcelable("scope", Immutable.INSTANCE.get(key));
    return bundle;
  }

  /**
   * Applies a function to the scope's state.
   */
  public ApplyResult<S> apply(Function<S> function) {
    return immutable.apply(key, function);
  }

  /**
   * Executes a background background.
   * <p>
   * Sticky means that the request will not be removed after it's execution and will be re-executed
   * on a process termination.
   */
  public void background(int id, Background<S> background) {
    immutable.background(key, id, background);
  }

  /**
   * Drops a background task.
   * The task will be executed without interruption as usual, but it's application function will not be called.
   */
  public void drop(int id) {
    immutable.drop(key, id);
  }

  /**
   * Finally dispose the scope. Do this on Activity.onDestroy when isFinishing() is true.
   * If the scope is controlled by a Fragment then you need to manually control it's existence.
   */
  public void remove() {
    immutable.remove(key);
  }

  /**
   * Registers a callback for state updates.
   * Once registered the callback will be fired immediately.
   */
  public void register(StateCallback<S> callback) {
    immutable.register(key, callback);
  }

  /**
   * Unregisters a given state callback.
   */
  public void unregister(StateCallback<S> callback) {
    immutable.unregister(key, callback);
  }
}
