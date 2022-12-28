package com.shahin.recyclerviewexample.carousel;
/*
 *created at 25/09/22
 *Created by @shahin
 */

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class DefaultChildSelectionListenerCustom extends CarouselChildSelectionListenerCustom {

    @NonNull
    private final OnCenterItemClickListener mOnCenterItemClickListener;

    protected DefaultChildSelectionListenerCustom(@NonNull final OnCenterItemClickListener onCenterItemClickListener, @NonNull final RecyclerView recyclerView, @NonNull final CarouselLayoutManagerCustom carouselLayoutManager) {
        super(recyclerView, carouselLayoutManager);

        mOnCenterItemClickListener = onCenterItemClickListener;
    }

    @Override
    protected void onCenterItemClicked(@NonNull final RecyclerView recyclerView, @NonNull final CarouselLayoutManagerCustom carouselLayoutManager, @NonNull final View v) {
        mOnCenterItemClickListener.onCenterItemClicked(recyclerView, carouselLayoutManager, v);
    }

    @Override
    protected void onBackItemClicked(@NonNull final RecyclerView recyclerView, @NonNull final CarouselLayoutManagerCustom carouselLayoutManager, @NonNull final View v) {
        recyclerView.smoothScrollToPosition(carouselLayoutManager.getPosition(v));
    }

    public static DefaultChildSelectionListenerCustom initCenterItemListener(@NonNull final OnCenterItemClickListener onCenterItemClickListener, @NonNull final RecyclerView recyclerView, @NonNull final CarouselLayoutManagerCustom carouselLayoutManager) {
        return new DefaultChildSelectionListenerCustom(onCenterItemClickListener, recyclerView, carouselLayoutManager);
    }

    public interface OnCenterItemClickListener {

        void onCenterItemClicked(@NonNull final RecyclerView recyclerView, @NonNull final CarouselLayoutManagerCustom carouselLayoutManager, @NonNull final View v);
    }
}
