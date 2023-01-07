package com.twaun95.moviestar.presentation.ui.bookmark

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.twaun95.moviestar.R
import com.twaun95.moviestar.application.Logger
import com.twaun95.moviestar.databinding.FragmentBookmarkBinding
import com.twaun95.moviestar.presentation.adapter.MovieGridLayoutManager
import com.twaun95.moviestar.presentation.adapter.MovieListAdapter
import com.twaun95.moviestar.presentation.base.BaseFragment
import com.twaun95.moviestar.presentation.dialog.DialogBookMark
import com.twaun95.moviestar.presentation.ui.main.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class BookMarkFragment : BaseFragment<FragmentBookmarkBinding, BookMarkFragmentViewModel>(R.layout.fragment_bookmark) {
    override val fragmentVM by viewModels<BookMarkFragmentViewModel>()
    private val activityVM by activityViewModels<MainActivityViewModel>()

    private val movieBookMarkedListAdapter by lazy { MovieListAdapter() }

    override fun initView() {
        super.initView()

        binding.activityVM = this.activityVM
        setRecyclerView()
    }

    override fun setObserver() {
        super.setObserver()

        activityVM.movieBookMarkList
            .onEach {
                movieBookMarkedListAdapter.submitList(it)
            }
            .launchIn(this.lifecycleScope)
    }

    private fun setRecyclerView() {
        binding.listBookmarkMovie.apply {
            layoutManager = MovieGridLayoutManager(requireContext())
            adapter = movieBookMarkedListAdapter.apply {
                onItemClickListener = { movie, position ->
                    DialogBookMark.show(
                        parentFragmentManager,
                        DialogBookMark.TYPE.DELETE,
                        {}, {

                            activityVM.removeBookMark(movie)

                            activityVM.updateBookMark(true, movie)
                            movie.isBookMarked = false
                            movieBookMarkedListAdapter.notifyItemChanged(position)
                        }
                    )
                }
            }
        }
    }

    companion object {
        fun getInstance() : BookMarkFragment = BookMarkFragment()
    }
}