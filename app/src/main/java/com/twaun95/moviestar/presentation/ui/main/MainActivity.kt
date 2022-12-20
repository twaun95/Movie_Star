package com.twaun95.moviestar.presentation.ui.main

import androidx.activity.viewModels
import com.twaun95.moviestar.R
import com.twaun95.moviestar.databinding.ActivityMainBinding
import com.twaun95.moviestar.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val viewModel by viewModels<MainActivityViewModel>()

    override fun initView() {
        super.initView()
    }

    override fun setEvent() {
        super.setEvent()
    }

    override fun setObserver() {
        super.setObserver()
    }
}