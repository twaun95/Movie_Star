package com.twaun95.moviestar.presentation.adapter

import android.view.View
import androidx.databinding.BindingAdapter
import com.twaun95.moviestar.application.Logger
import com.twaun95.moviestar.presentation.model.Mode
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
fun setOnClickSearch(view: ToolBar, viewModel: MainActivityViewModel) {
    view.searchButton.setOnClickListener {
        Logger.d("setOnClickSearch ${view.searchText.text}")
        viewModel.searchMovie(view.searchText.text.toString().trim())
    }
}