package com.twaun95.moviestar.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.twaun95.moviestar.presentation.ui.bookmark.BookMarkFragment
import com.twaun95.moviestar.presentation.ui.search.SearchFragment

class MovieViewPageAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment){
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> SearchFragment.getInstance()
            else -> BookMarkFragment.getInstance()
        }
    }
}