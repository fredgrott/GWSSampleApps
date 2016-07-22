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

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.github.shareme.gwssampleapps.mymateriallibrary.R;


/**
 * Created by fgrott on 7/22/2016.
 */
@SuppressWarnings("unused")
public abstract class ItemProcessor {

  private Context context;
  private LayoutInflater li;
  private final int defaultLayout;

  public ItemProcessor(Context context) {
    this.context = context;
    li = LayoutInflater.from(context);
    defaultLayout = R.layout.md_listitem;
  }

  protected final Context getContext() {
    return context;
  }

  /**
   * Returning 0 will use the default layout.
   *
   * @param forIndex The index of the item being inflated.
   */
  protected abstract int getLayout(int forIndex);

  /**
   * Called when the view is inflated and will soon be added to the list. You can setup views in your
   * list item here.
   *
   * @param forIndex The index of the item being inflated.
   * @param itemText The text associated with the current item from the array passed into items() from the Builder.
   * @param view The inflated view for the current item.
   */
  protected abstract void onViewInflated(int forIndex, String itemText, View view);

  /**
   * Used by MaterialDialog to inflate a list item view that will be displayed in a list.
   *
   * @param forIndex The index of the item being inflated.
   * @param itemText The text associated with the current item from the array passed into items() from the Builder.
   */
  public final View inflateItem(int forIndex, String itemText) {
    int itemLayout = getLayout(forIndex);
    if (itemLayout == 0) itemLayout = defaultLayout;
    View view = li.inflate(itemLayout, null);
    onViewInflated(forIndex, itemText, view);
    return view;
  }
}
