/*
  The MIT License (MIT)

Copyright (c) 2014 Aidan Michael Follestad
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
package com.github.shareme.gwssampleapps.mymateriallibrary.mdialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;


/**
 * Created by fgrott on 7/22/2016.
 */

public class DialogBase extends AlertDialog implements DialogInterface.OnShowListener {

  protected final static String POSITIVE = "POSITIVE";
  protected final static String NEGATIVE = "NEGATIVE";
  protected final static String NEUTRAL = "NEUTRAL";
  private OnShowListener mShowListener;

  protected DialogBase(Context context) {
    super(context);
  }

  public static void setMargin(View view, int top, int bottom, int left, int right) {
    setMargin(view, top, bottom, left, right, -1);
  }

  public static void setMargin(View view, int top, int bottom, int left, int right, int height) {
    ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
    if (top > -1) params.topMargin = top;
    if (bottom > -1) params.bottomMargin = bottom;
    if (left > -1) params.leftMargin = left;
    if (right > -1) params.rightMargin = right;
    if (height > -1) params.height = height;
    view.setLayoutParams(params);
  }

  /**
   * @deprecated Use getActionButton(com.afollestad.materialdialogs.DialogAction)} instead.
   */
  @Override
  public Button getButton(int whichButton) {
    throw new RuntimeException("Use getActionButton(MaterialDialog.Button) instead.");
  }

  /**
   * @deprecated Not supported by the Material dialog.
   */
  @Override
  public void setView(View view) {
    throw new RuntimeException("This method is not supported by the MaterialDialog.");
  }

  protected void setViewInternal(View view) {
    super.setView(view);
  }

  /**
   * @deprecated Not supported by the Material dialog.
   */
  @Override
  public void setView(View view, int viewSpacingLeft, int viewSpacingTop, int viewSpacingRight, int viewSpacingBottom) {
    throw new RuntimeException("This method is not supported by the MaterialDialog.");
  }

  /**
   * @deprecated Not supported by the Material dialog.
   */
  @Override
  public void setMessage(CharSequence message) {
    throw new RuntimeException("This method is not supported by the MaterialDialog.");
  }

  /**
   * @deprecated Not supported by the Material dialog.
   */
  @Override
  public void setTitle(CharSequence title) {
    throw new RuntimeException("This method is not supported by the MaterialDialog.");
  }

  /**
   * @deprecated Not supported by the Material dialog.
   */
  @Override
  public void setCustomTitle(View customTitleView) {
    throw new RuntimeException("This method is not supported by the MaterialDialog.");
  }

  /**
   * @deprecated Not supported by the Material dialog.
   */
  @Override
  public void setIcon(Drawable icon) {
    throw new RuntimeException("This method is not supported by the MaterialDialog.");
  }

  /**
   * @deprecated Not supported by the Material dialog.
   */
  @Override
  public void setIcon(int resId) {
    throw new RuntimeException("This method is not supported by the MaterialDialog.");
  }

  /**
   * @deprecated Not supported by the Material dialog.
   */
  @Override
  public void setIconAttribute(int attrId) {
    throw new RuntimeException("This method is not supported by the MaterialDialog.");
  }

  /**
   * @deprecated Not supported by the Material dialog.
   */
  @Override
  public void setButton(int whichButton, CharSequence text, Message msg) {
    throw new RuntimeException("Use setActionButton(MaterialDialog.Button, CharSequence) instead.");
  }

  /**
   * @deprecated Not supported by the Material dialog.
   */
  @Override
  public void setButton(int whichButton, CharSequence text, OnClickListener listener) {
    throw new RuntimeException("Use setActionButton(MaterialDialog.Button, CharSequence) instead.");
  }

  /**
   * @deprecated Not supported by the Material dialog.
   */
  @Override
  public ListView getListView() {
    throw new RuntimeException("This method is not supported by the MaterialDialog.");
  }

  @Override
  public final void setOnShowListener(OnShowListener listener) {
    mShowListener = listener;
  }

  protected final void setOnShowListenerInternal() {
    super.setOnShowListener(this);
  }

  @Override
  public void onShow(DialogInterface dialog) {
    if (mShowListener != null)
      mShowListener.onShow(dialog);
  }
}
