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

import android.os.Parcel;
import android.os.Parcelable;

/**
 * An utility class that represents a reference to a value that should not be parceled.
 * Created by fgrott on 8/3/2016.
 */
@SuppressWarnings("unused")
public class Transient<T> implements Parcelable {

  private static final Transient EMPTY = new Transient();

  public final T value;

  public Transient() {
    value = null;
  }

  public T get() {
    return value;
  }

  public boolean isPresent() {
    return value != null;
  }

  @SuppressWarnings("unchecked")
  public static <T> Transient<T> empty() {
    return EMPTY;
  }

  public static <T> Transient<T> of(T value) {
    return value == null ? EMPTY : new Transient<>(value);
  }

  protected Transient(Parcel ignored, boolean fromParcel) {
    this.value = null;
  }

  private Transient(T value) {
    this.value = value;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public static final Creator<Transient> CREATOR = new Creator<Transient>() {
    @Override
    public Transient createFromParcel(Parcel in) {
      return new Transient(in, true);
    }

    @Override
    public Transient[] newArray(int size) {
      return new Transient[size];
    }
  };

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Transient<?> that = (Transient<?>) o;

    return !(value != null ? !value.equals(that.value) : that.value != null);
  }

  @Override
  public int hashCode() {
    return value != null ? value.hashCode() : 0;
  }

  @Override
  public String toString() {
    return "Transient{" +
            "value=" + value +
            '}';
  }
}
