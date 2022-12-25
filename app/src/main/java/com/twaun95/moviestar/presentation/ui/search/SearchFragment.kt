package com.twaun95.moviestar.presentation.ui.search

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.twaun95.moviestar.R
import com.twaun95.moviestar.application.Logger
import com.twaun95.moviestar.databinding.FragmentSearchBinding
import com.twaun95.moviestar.presentation.adapter.MovieGridLayoutManager
import com.twaun95.moviestar.presentation.adapter.MovieListAdapter
import com.twaun95.moviestar.presentation.base.BaseFragment
import com.twaun95.moviestar.presentation.dialog.DialogBookMark
import com.twaun95.moviestar.presentation.ui.main.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding, SearchFragmentViewModel>(R.layout.fragment_search){
    override val fragmentVM by viewModels<SearchFragmentViewModel>()
    private val activityVM by activityViewModels<MainActivityViewModel>()

    private val movieSearchedListAdapter by lazy { MovieListAdapter() }

    override fun initView() {
        super.initView()

        binding.activityVM = this.activityVM
        setRecyclerView()
    }

    override fun setObserver() {
        super.setObserver()

        activityVM.movieList
            .onEach {
                movieSearchedListAdapter.submitList(it)
            }
            .launchIn(this.lifecycleScope)

        activityVM.updatedMoviePosition.observe(viewLifecycleOwner) {
            movieSearchedListAdapter.notifyItemChanged(it)
        }
    }

    private fun setRecyclerView() {
        binding.listSearchMovie.apply {
            layoutManager = MovieGridLayoutManager(requireContext())
            adapter = movieSearchedListAdapter.apply {
                onItemClickListener = { movie, position ->
                    DialogBookMark.show(
                        parentFragmentManager,
                        if (movie.isBookMarked) DialogBookMark.TYPE.DELETE else DialogBookMark.TYPE.ADD,
                        { }, {
                            activityVM.updateBookMark(movie.isBookMarked, movie)
                            movie.isBookMarked = movie.isBookMarked == false
//                            movieSearchedListAdapter.notifyItemChanged(position)
                        }
                    )
                }
            }
        }
    }

    companion object {
        fun getInstance() : SearchFragment = SearchFragment()
    }
}