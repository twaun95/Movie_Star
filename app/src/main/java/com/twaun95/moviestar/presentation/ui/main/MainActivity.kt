package com.twaun95.moviestar.presentation.ui.main

import androidx.activity.viewModels
import com.twaun95.moviestar.R
import com.twaun95.moviestar.application.Logger
import com.twaun95.moviestar.databinding.ActivityMainBinding
import com.twaun95.moviestar.presentation.adapter.MovieViewPageAdapter
import com.twaun95.moviestar.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val viewModel by viewModels<MainActivityViewModel>()

    override fun initView() {
        super.initView()

        initViewPager()
        initBottomNavigation()
        Logger.d(viewModel.test)
    }

    override fun setEvent() {
        super.setEvent()
    }

    override fun setObserver() {
        super.setObserver()
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
                    R.id.page_timer -> {
                        binding.viewPager.setCurrentItem(0, false)
                        true
                    }
                    R.id.page_stop_watch -> {
                        binding.viewPager.setCurrentItem(1, false)
                        true
                    }
                    else -> { false }
                }
            }
        }
    }
}