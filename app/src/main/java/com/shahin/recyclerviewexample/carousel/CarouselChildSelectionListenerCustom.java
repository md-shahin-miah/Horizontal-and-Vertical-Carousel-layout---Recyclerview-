
/*
 *created at 25/09/22
 *Created by @shahin
 */

package com.shahin.recyclerviewexample.carousel;


import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public abstract class CarouselChildSelectionListenerCustom {

    @NonNull
    private final RecyclerView mRecyclerView;
    @NonNull
    private final CarouselLayoutManagerCustom mCarouselLayoutManager;

    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(final View v) {
            final RecyclerView.ViewHolder holder = mRecyclerView.getChildViewHolder(v);
            final int position = holder.getAdapterPosition();

            if (position == mCarouselLayoutManager.getCenterItemPosition()) {
                onCenterItemClicked(mRecyclerView, mCarouselLayoutManager, v);
            } else {
                onBackItemClicked(mRecyclerView, mCarouselLayoutManager, v);
            }
        }
    };

    protected CarouselChildSelectionListenerCustom(@NonNull final RecyclerView recyclerView, @NonNull final CarouselLayoutManagerCustom carouselLayoutManager) {
        mRecyclerView = recyclerView;
        mCarouselLayoutManager = carouselLayoutManager;

        mRecyclerView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(@NonNull final View view) {
                view.setOnClickListener(mOnClickListener);
            }

            @Override
            public void onChildViewDetachedFromWindow(@NonNull final View view) {
                view.setOnClickListener(null);
            }
        });
    }

    protected abstract void onCenterItemClicked(@NonNull final RecyclerView recyclerView, @NonNull final CarouselLayoutManagerCustom carouselLayoutManager, @NonNull final View v);

    protected abstract void onBackItemClicked(@NonNull final RecyclerView recyclerView, @NonNull final CarouselLayoutManagerCustom carouselLayoutManager, @NonNull final View v);
}