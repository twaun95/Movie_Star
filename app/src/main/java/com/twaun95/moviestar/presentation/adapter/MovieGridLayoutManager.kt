package com.twaun95.moviestar.presentation.adapter

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MovieGridLayoutManager(
    context: Context
) : GridLayoutManager(context, 2, VERTICAL, false) {

    override fun checkLayoutParams(lp: RecyclerView.LayoutParams): Boolean {
        lp.height = (height/2.1).toInt()
        return super.checkLayoutParams(lp)
    }
}