package com.universal.lib.core;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by jixiongxu on 2022/8/8.
 * Copyright (c) 2022 UniversalRecycleview. All rights reserved.
 *
 * @author jixiongxu
 * @since 2022/8/8
 */
@SuppressWarnings("unused")
public class HorizontalListSpacingInset extends RecyclerView.ItemDecoration {
    private final int mSpacing;

    private final int mFirstSpacing;

    public HorizontalListSpacingInset(int spacing, int firstSpacing) {
        mSpacing = spacing;
        mFirstSpacing = firstSpacing;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        int itemCount = state.getItemCount();
        if (position == 0) {
            outRect.left = mFirstSpacing;
        } else {
            outRect.left = mSpacing;
        }
        outRect.right = mSpacing;
    }
}
