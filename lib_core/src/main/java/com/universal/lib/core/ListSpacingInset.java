package com.universal.lib.core;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * RecyclerView设置List布局的间隔
 * <p>
 * Created by jixiongxu on 2022/8/8.
 * Copyright (c) 2022 UniversalRecycleview. All rights reserved.
 *
 * @author jixiongxu
 * @since 2022/8/8
 */
@SuppressWarnings("unused")
public class ListSpacingInset extends RecyclerView.ItemDecoration {
    protected final int mSpacing;
    protected final int mFirstExtraSpacing;
    protected final int mLastExtraSpacing;

    public ListSpacingInset(int spacing) {
        this(spacing, 0, 0);
    }

    public ListSpacingInset(int spacing, int firstExtraSpacing, int lastExtraSpacing) {
        mSpacing = spacing;
        mFirstExtraSpacing = firstExtraSpacing;
        mLastExtraSpacing = lastExtraSpacing;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        int itemCount = state.getItemCount();
        outRect.bottom = mSpacing;
        if (position == 0) {
            outRect.top = mFirstExtraSpacing;
        }
        if (position == itemCount - 1) {
            outRect.bottom += mLastExtraSpacing;
        }
    }

    public static class Horizontal extends ListSpacingInset {

        public Horizontal(int spacing) {
            this(spacing, 0, 0);
        }

        public Horizontal(int spacing, int firstExtraSpacing, int lastExtraSpacing) {
            super(spacing, firstExtraSpacing, lastExtraSpacing);
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view);
            int itemCount = state.getItemCount();
            outRect.right = mSpacing;
            if (position == 0) {
                outRect.left = mFirstExtraSpacing;
            }
            if (position == itemCount - 1) {
                outRect.right += mLastExtraSpacing;
            }
        }
    }

    public static class Vertical extends ListSpacingInset {

        public Vertical(int spacing) {
            this(spacing, 0, 0);
        }

        public Vertical(int spacing, int firstExtraSpacing, int lastExtraSpacing) {
            super(spacing, firstExtraSpacing, lastExtraSpacing);
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view);
            int itemCount = state.getItemCount();
            outRect.right = mSpacing;
            if (position == 0) {
                outRect.left = mFirstExtraSpacing;
            }
            if (position == itemCount - 1) {
                outRect.right += mLastExtraSpacing;
            }
        }
    }

    public static class GridSpacingInset extends ListSpacingInset {

        private int mSpanCount = 1;

        public GridSpacingInset(int spacing, int spanCount) {
            this(spacing, 0, 0);
            mSpanCount = spanCount;
        }

        public GridSpacingInset(int spacing, int firstExtraSpacing, int lastExtraSpacing) {
            super(spacing, firstExtraSpacing, lastExtraSpacing);
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view);
            int itemCount = state.getItemCount();
            if (position % mSpanCount == 0) {
                outRect.left = 0;
            } else {
                outRect.left = mSpacing / 2;
            }
            if (position % mSpanCount == (mSpanCount - 1)) {
                outRect.right = 0;
            } else {
                outRect.right = mSpacing / 2;
            }
            boolean isRTL = parent.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL;
            if (isRTL) {
                int left = outRect.left;
                outRect.left = outRect.right;
                outRect.right = left;
            }
            outRect.bottom = mSpacing;
        }
    }
}
