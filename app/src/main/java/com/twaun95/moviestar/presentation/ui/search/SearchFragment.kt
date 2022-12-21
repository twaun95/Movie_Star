package com.twaun95.moviestar.presentation.ui.search

import androidx.fragment.app.viewModels
import com.twaun95.moviestar.R
import com.twaun95.moviestar.databinding.FragmentSearchBinding
import com.twaun95.moviestar.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding, SearchFragmentViewModel>(R.layout.fragment_search){
    override val fragmentVM by viewModels<SearchFragmentViewModel>()

    companion object {
        fun getInstance() : SearchFragment = SearchFragment()
    }
}