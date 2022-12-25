package com.twaun95.moviestar.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.twaun95.moviestar.R
import com.twaun95.moviestar.application.Logger
import com.twaun95.moviestar.databinding.ViewToolbarBinding
import com.twaun95.moviestar.presentation.model.Mode

class ToolBar(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {

    private var binding: ViewToolbarBinding
    val searchText: EditText
        get() = binding.editTextSearch
    val searchButton: Button
        get() = binding.buttonSearch

    init {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.view_toolbar, this, true)
    }

    fun updateState(mode: Mode) {
        if (mode==Mode.SEARCH) {
            binding.layoutSearch.visibility = VISIBLE
            binding.layoutTitle.visibility = GONE
        } else {
            binding.layoutTitle.visibility = VISIBLE
            binding.layoutSearch.visibility = GONE
        }
    }
}