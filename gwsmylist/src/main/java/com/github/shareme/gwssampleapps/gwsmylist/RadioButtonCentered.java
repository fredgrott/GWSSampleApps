package com.github.shareme.gwssampleapps.gwsmylist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.RadioButton;

/**
 * Created by fgrott on 7/29/2016.
 */
@SuppressWarnings("unused")
public class RadioButtonCentered extends RadioButton {

  @SuppressWarnings("ResourceType")
  public RadioButtonCentered(Context context, AttributeSet attrs) {
    super(context, attrs);
    @SuppressLint("Recycle") TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CompoundButton, 0, 0);
    buttonDrawable = a.getDrawable(1);
    setButtonDrawable(android.R.color.transparent);
  }
  Drawable buttonDrawable;


  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    if (buttonDrawable != null) {
      buttonDrawable.setState(getDrawableState());
      final int verticalGravity = getGravity() & Gravity.VERTICAL_GRAVITY_MASK;
      final int height = buttonDrawable.getIntrinsicHeight();

      int y = 0;

      switch (verticalGravity) {
        case Gravity.BOTTOM:
          y = getHeight() - height;
          break;
        case Gravity.CENTER_VERTICAL:
          y = (getHeight() - height) / 2;
          break;
      }

      int buttonWidth = buttonDrawable.getIntrinsicWidth();
      int buttonLeft = (getWidth() - buttonWidth) / 2;
      buttonDrawable.setBounds(buttonLeft, y, buttonLeft+buttonWidth, y + height);
      buttonDrawable.draw(canvas);
    }
  }
}