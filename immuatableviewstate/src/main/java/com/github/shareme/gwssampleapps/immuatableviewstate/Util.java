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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A set of utility methods to create modified collections instead of mutating them directly. Its
 * the SECRET SAUCE to how the View-State is constrained to be immutable. A mutable collections object
 * is not being used but one is creating a new HashMap and creating an umodifiable collections object
 * instead.
 *
 * in short words we get a READ-ONLY view of the Collections Map or List object.
 *
 *
 *
 * Created by fgrott on 8/3/2016.
 */
@SuppressWarnings("unused")
class Util {

  static <K, V> Map<K, V> with(Map<K, V> src, K key, V value) {
    HashMap<K, V> dst = new HashMap<>(src);
    dst.put(key, value);
    return Collections.unmodifiableMap(dst);
  }

  static <K, V> Map<K, V> without(Map<K, V> src, K key) {
    HashMap<K, V> dst = new HashMap<>(src);
    dst.remove(key);
    return Collections.unmodifiableMap(dst);
  }

  static <T> List<T> with(List<T> src, T value) {
    ArrayList<T> dst = new ArrayList<>(src);
    dst.add(value);
    return Collections.unmodifiableList(dst);
  }

  static <T> List<T> without(List<T> src, T value) {
    ArrayList<T> dst = new ArrayList<>(src);
    dst.remove(value);
    return Collections.unmodifiableList(dst);
  }
}
