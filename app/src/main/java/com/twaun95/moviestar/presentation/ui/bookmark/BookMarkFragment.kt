package com.twaun95.moviestar.presentation.ui.bookmark

import androidx.fragment.app.viewModels
import com.twaun95.moviestar.R
import com.twaun95.moviestar.databinding.FragmentBookmarkBinding
import com.twaun95.moviestar.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookMarkFragment : BaseFragment<FragmentBookmarkBinding, BookMarkFragmentViewModel>(R.layout.fragment_bookmark) {
    override val fragmentVM by viewModels<BookMarkFragmentViewModel>()

    companion object {
        fun getInstance() : BookMarkFragment = BookMarkFragment()
    }
}