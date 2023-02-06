package com.example.myrc_02.fragment.haru

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

private const val NUM_PAGES = 7

class SearchFragViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = NUM_PAGES

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HaruFirstFragment()
            1 -> HaruFirstFragment()
            2 -> HaruFirstFragment()
            3 -> HaruFirstFragment()
            4 -> HaruFirstFragment()
            5 -> HaruFirstFragment()
            else -> HaruFirstFragment()
        }
    }
}