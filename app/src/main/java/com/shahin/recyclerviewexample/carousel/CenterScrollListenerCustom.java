package com.shahin.recyclerviewexample.carousel;


import android.view.animation.DecelerateInterpolator;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/*
 *created at 25/09/22
 *Created by @shahin
 */

/**
 * Class for centering items after scroll event.<br />
 * This class will listen to current scroll state and if item is not centered after scroll it will automatically scroll it to center.
 */
public class CenterScrollListenerCustom extends RecyclerView.OnScrollListener {

    private boolean mAutoSet = true;

    @Override
    public void onScrollStateChanged(@NonNull final RecyclerView recyclerView, final int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (!(layoutManager instanceof CarouselLayoutManagerCustom)) {
            mAutoSet = true;
            return;
        }

        final CarouselLayoutManagerCustom lm = (CarouselLayoutManagerCustom) layoutManager;
        if (!mAutoSet) {
            if (RecyclerView.SCROLL_STATE_IDLE == newState) {
                final int scrollNeeded = lm.getOffsetCenterView();
                if (CarouselLayoutManagerCustom.HORIZONTAL == lm.getOrientation()) {
                    recyclerView.smoothScrollBy(scrollNeeded, 0);
                } else {
                    recyclerView.smoothScrollBy(0, scrollNeeded,new DecelerateInterpolator());
                }
                mAutoSet = true;
            }
        }
        if (RecyclerView.SCROLL_STATE_DRAGGING == newState || RecyclerView.SCROLL_STATE_SETTLING == newState) {
            mAutoSet = false;
        }
    }
}