package com.twaun95.moviestar.presentation.adapter

import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.twaun95.moviestar.R
import com.twaun95.moviestar.presentation.model.Mode
import com.twaun95.moviestar.presentation.ui.main.MainActivity
import com.twaun95.moviestar.presentation.ui.main.MainActivityViewModel
import com.twaun95.moviestar.presentation.view.ToolBar

@BindingAdapter("android:visible")
fun setVisibility(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("android:onChangedMode")
fun setOnChangedMode(view: ToolBar, mode: Mode) {
    view.updateState(mode)
}

@BindingAdapter("android:onSearch")
fun setOnClickSearch(view: ToolBar, activity: MainActivity) {
    view.searchButton.setOnClickListener {
        activity.searchMovie(view.searchText)
    }
}

@BindingAdapter("android:onKeyBoardSearch")
fun setOnKeyBoardSearch(view: ToolBar, activity: MainActivity) {
    view.searchText.setOnEditorActionListener { v, actionId, event ->
        when (actionId) {
            EditorInfo.IME_ACTION_SEARCH -> { activity.searchMovie(v) }
            else -> {}
        }
        true
    }
}

@BindingAdapter("app:setImageUrl")
fun setImageUrl(view: ImageView, url: String) {
    Glide.with(view.context)
        .asBitmap()
        .centerCrop()
        .load(url)
        .error(R.drawable.image_movie_thumbnail)
        .into(view)
}

@BindingAdapter("app:setBookMarkImage")
fun setBookMarkImage(view: ImageView, isBookMarked: Boolean) {
    Glide.with(view.context)
        .asBitmap()
        .load(if (isBookMarked)R.drawable.bookmark_on else R.drawable.bookmark_off)
        .into(view)
}