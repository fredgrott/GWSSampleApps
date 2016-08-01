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

import android.content.res.AssetManager;
import android.graphics.Typeface;

/**
 * Our custom fonts get enum defined here for the
 * view decorator machinery in activities and fragments to grab.
 *
 * We also define an attrs res file, an example
 *
 * <code>
 * <?xml version="1.0" encoding="utf-8"?>
 *  <resources>
 *     <declare-styleable name="Font">
 *          <attr format="enum" name="font" >
 *             <enum name="AnonymousProRegular" value="0" />
 *             <enum name="AvroRegular" value="1" />
 *             <enum name="ChivoRegular" value="2" />
 *             <enum name="VarelaRoundRegular" value="3" />
 *          </attr>
 *     </declare-styleable>
 *   </resources>
 *
 * </code>
 *
 * Created by fgrott on 7/22/2016.
 */
@SuppressWarnings("unused")
public enum GWSFont {
  ROBOTO_BLACK("fonts/Roboto-Black.ttf");

  private final String fontPath;

  GWSFont(String fontPath){
    this.fontPath = fontPath;
  }

  public Typeface getTypeFace(AssetManager assetManager) {
    return Typeface.createFromAsset(assetManager, fontPath);
  }


}
