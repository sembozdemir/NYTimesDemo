package com.sembozdemir.nytimesdemo.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sembozdemir.nytimesdemo.core.extensions.orZero

class VerticalSpacingItemDecoration(
    private val verticalSpacing: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val itemCount = parent.adapter?.itemCount.orZero()
        if (parent.getChildAdapterPosition(view) != itemCount - 1) {
            outRect.bottom = verticalSpacing
        }
    }
}