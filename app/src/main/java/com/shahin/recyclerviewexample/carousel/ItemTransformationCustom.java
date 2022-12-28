package com.shahin.recyclerviewexample.carousel;
/*
 *created at 25/09/22
 *Created by @shahin
 */
public class ItemTransformationCustom {
    public final float mScaleX;
    public final float mScaleY;
   public final float mTranslationX;
   public final float mTranslationY;

    public ItemTransformationCustom(final float scaleX, final float scaleY, final float translationX, final float translationY) {
        mScaleX = scaleX;
        mScaleY = scaleY;
        mTranslationX = translationX;
        mTranslationY = translationY;
    }
}
