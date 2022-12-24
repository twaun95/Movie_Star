package com.twaun95.moviestar.presentation.ui.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.twaun95.moviestar.R
import com.twaun95.moviestar.application.Logger
import com.twaun95.moviestar.databinding.FragmentBookmarkBinding
import com.twaun95.moviestar.presentation.base.BaseFragment
import com.twaun95.moviestar.presentation.ui.main.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookMarkFragment : BaseFragment<FragmentBookmarkBinding, BookMarkFragmentViewModel>(R.layout.fragment_bookmark) {
    override val fragmentVM by viewModels<BookMarkFragmentViewModel>()
    private val activityVM by activityViewModels<MainActivityViewModel>()

    companion object {
        fun getInstance() : BookMarkFragment = BookMarkFragment()
    }
}