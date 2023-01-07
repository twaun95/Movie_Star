package com.twaun95.moviestar.presentation.ui.main

import android.graphics.Rect
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import com.twaun95.moviestar.R
import com.twaun95.moviestar.application.Logger
import com.twaun95.moviestar.databinding.ActivityMainBinding
import com.twaun95.moviestar.presentation.adapter.MovieListAdapter
import com.twaun95.moviestar.presentation.adapter.MovieViewPageAdapter
import com.twaun95.moviestar.presentation.base.BaseActivity
import com.twaun95.moviestar.presentation.model.Mode
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val viewModel by viewModels<MainActivityViewModel>()

    override fun initView() {
        super.initView()

        binding.activity = this
        binding.viewModel = this.viewModel

        initViewPager()
        initBottomNavigation()
    }

    override fun setObserver() {
        super.setObserver()

        viewModel.error.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun initViewPager() {
        binding.viewPager.apply {
            isUserInputEnabled = false
            adapter = MovieViewPageAdapter(this@MainActivity)
        }
    }

    private fun initBottomNavigation() {
        binding.bottomNavigation.apply {
            itemIconTintList = null
            setOnItemSelectedListener { page ->
                when(page.itemId) {
                    R.id.page_search -> {
                        binding.viewPager.setCurrentItem(0, false)
                        viewModel.viewMode.postValue(Mode.SEARCH)
                        true
                    }
                    R.id.page_bookmark -> {
                        binding.viewPager.setCurrentItem(1, false)
                        viewModel.viewMode.postValue(Mode.BOOKMARK)

                        viewModel.getBookMarkMovies()

                        true
                    }
                    else -> { false }
                }
            }
        }
    }

    // 키보드 바깥 영역 터치 시 키보드 자판 내리기
    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is EditText) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    v.clearFocus()
                    val imm: InputMethodManager =
                        getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0)
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }

    // 영화 검색(Binding Adapter)
    fun searchMovie(v: TextView) {
        val imm: InputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0)

        viewModel.searchMovie(v.text.toString().trim())
    }
}