package com.shahin.recyclerviewexample.carousel;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;



/*
 *created at 25/09/22
 *Created by @shahin
 */
public class CarouselZoomPostLayoutListenerCustom extends CarouselLayoutManagerCustom.PostLayoutListener {

    private static final String TAG = "CarouselZoomPostLayoutL";
    private final float mScaleMultiplier;

    public CarouselZoomPostLayoutListenerCustom() {
        this(0.10f);
    }

    public CarouselZoomPostLayoutListenerCustom(final float scaleMultiplier) {
        mScaleMultiplier = scaleMultiplier;
    }

    @Override
    public ItemTransformationCustom transformChild(@NonNull final View child, final float itemPositionToCenterDiff, final int orientation) {
//        final float scale = 1.0f - mScaleMultiplier * Math.abs(itemPositionToCenterDiff);
//
//        // because scaling will make view smaller in its center, then we should move this item to the top or bottom to make it visible
//        final float translateY;
//        final float translateX;
//        if (CarouselLayoutManagerCustom.VERTICAL == orientation) {
////            final float translateYGeneral = child.getMeasuredHeight() * (1 - scale) / 2f;
//            final float translateYGeneral = child.getMeasuredWidth() * (1 - scale) / 0.8f;
//
//
//            translateY = Math.signum(itemPositionToCenterDiff) * translateYGeneral;
//            translateX = 0;
//        } else {
//            final float translateXGeneral = child.getMeasuredWidth() * (1 - scale) / 2f;
//            translateX = Math.signum(itemPositionToCenterDiff) * translateXGeneral;
//            translateY = 0;
//        }
//
//        return new ItemTransformationCustom(scale, scale, translateX, translateY);

        final float scale = 1.0f - 0.09f * Math.abs(itemPositionToCenterDiff);
        Log.d(TAG, "transformChild: scale "+scale);
        Log.d(TAG, "transformChild: center diff "+itemPositionToCenterDiff);

        // because scaling will make view smaller in its center, then we should move this item to the top or bottom to make it visible
        final float translateY;
        final float translateX;
        if (CarouselLayoutManagerCustom.VERTICAL == orientation) {
            Log.d(TAG, "transformChild: one ");
//            final float translateYGeneral = child.getMeasuredHeight() * (1 - scale) / 3f;
            final float translateYGeneral = child.getMeasuredWidth() * (1 - scale) / 3f;
            translateY = Math.signum(itemPositionToCenterDiff) * translateYGeneral;
            translateX = 0;
        } else {
            Log.d(TAG, "transformChild: two ");

            final float translateXGeneral = child.getMeasuredWidth() * (1 - scale) / 2f;
            translateX = Math.signum(itemPositionToCenterDiff-1) * translateXGeneral;
            translateY = 0;
        }

        Log.d(TAG, "transformChild: translateY "+translateY);

        return new ItemTransformationCustom(scale, scale, translateX, translateY);

    }
}