package com.saila.third.expandableselector.animation;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Animation extension created to resize a widget in two dimensions given the from and to width and
 * height. This Animation changes the width and the height associated to the widget and invokes
 * requestLayout() method to redraw it.
 */
public class ResizeAnimation extends Animation {

  private static final long DEFAULT_DURATION_IN_MS = 250;

  private View view;
  private float toHeight;
  private float fromHeight;
  private float toWidth;
  private float fromWidth;

  public ResizeAnimation(View view, float toWidth, float toHeight) {
    this.toHeight = toHeight;
    this.toWidth = toWidth;
    this.fromHeight = view.getHeight();
    this.fromWidth = view.getWidth();
    this.view = view;
    setDuration(DEFAULT_DURATION_IN_MS);
  }

  @Override protected void applyTransformation(float interpolatedTime, Transformation t) {
    float height = (toHeight - fromHeight) * interpolatedTime + fromHeight;
    float width = (toWidth - fromWidth) * interpolatedTime + fromWidth;
    ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
    layoutParams.height = (int) height;
    layoutParams.width = (int) width;
    view.requestLayout();
  }

  @Override public boolean willChangeBounds() {
    return true;
  }
}
