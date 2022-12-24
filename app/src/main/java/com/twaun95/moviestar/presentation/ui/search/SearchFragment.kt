package com.twaun95.moviestar.presentation.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.twaun95.moviestar.R
import com.twaun95.moviestar.application.Logger
import com.twaun95.moviestar.databinding.FragmentSearchBinding
import com.twaun95.moviestar.presentation.adapter.MovieGridLayoutManager
import com.twaun95.moviestar.presentation.adapter.MovieListAdapter
import com.twaun95.moviestar.presentation.base.BaseFragment
import com.twaun95.moviestar.presentation.ui.main.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onEmpty

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding, SearchFragmentViewModel>(R.layout.fragment_search){
    override val fragmentVM by viewModels<SearchFragmentViewModel>()
    private val activityVM by activityViewModels<MainActivityViewModel>()

    private val movieListAdapter by lazy { MovieListAdapter() }

    override fun initView() {
        super.initView()

        binding.activityVM = this.activityVM
        setRecyclerView()
    }

    override fun setEvent() {
        super.setEvent()
    }

    override fun setObserver() {
        super.setObserver()

        activityVM.movieList
            .onEach {
                movieListAdapter.submitList(it)
            }
            .launchIn(this.lifecycleScope)
    }

    private fun setRecyclerView() {
        binding.listSearchMovie.apply {
            layoutManager = MovieGridLayoutManager(requireContext())
            adapter = movieListAdapter.apply {
                onItemClickListener = {
                    Logger.d(it)
                }
            }
        }
    }

    companion object {
        fun getInstance() : SearchFragment = SearchFragment()
    }
}