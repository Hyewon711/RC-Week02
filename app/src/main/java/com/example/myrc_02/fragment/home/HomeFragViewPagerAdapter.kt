package com.example.myrc_02.fragment.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter


private const val NUM_PAGES = 6

class SearchFragViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = NUM_PAGES

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FirstFragment()
            1 -> GiftFragment()
            2 -> BestFragment()
            3 -> New5Fragment()
            4 -> SaleFragment()
            else -> StoreFragment()
        }
    }
}