package com.twaun95.moviestar.presentation.ui.search

import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.twaun95.moviestar.R
import com.twaun95.moviestar.application.Logger
import com.twaun95.moviestar.databinding.FragmentSearchBinding
import com.twaun95.moviestar.presentation.adapter.MovieGridLayoutManager
import com.twaun95.moviestar.presentation.adapter.MovieListAdapter
import com.twaun95.moviestar.presentation.base.BaseFragment
import com.twaun95.moviestar.presentation.dialog.DialogBookMark
import com.twaun95.moviestar.presentation.extensions.setOnSingleClickListener
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
                onItemClickListener = { movie, _ ->
                    DialogBookMark.show(
                        parentFragmentManager,
                        if (movie.isBookMarked) DialogBookMark.TYPE.DELETE else DialogBookMark.TYPE.ADD,
                        { }, {
                            activityVM.updateBookMark(movie.isBookMarked, movie)
                            movie.isBookMarked = movie.isBookMarked == false
                        }
                    )
                }
            }

            addOnScrollListener(object: RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if(!binding.listSearchMovie.canScrollVertically(DIRECTION_VERTICAL) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                        activityVM.searchNextPage()
                    }
                }
            })
        }
    }

    companion object {
        fun getInstance() : SearchFragment = SearchFragment()

        private const val DIRECTION_VERTICAL = 1
    }
}