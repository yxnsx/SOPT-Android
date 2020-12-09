package com.yxnsx.sopt.week03

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter


class HomeViewPagerAdapter(fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {


    override fun getItem(position: Int): Fragment = when(position){
        0 -> BottomNavProfileFragment()
        1 -> BottomNavRecyclerViewFragment()
        2 -> BottomNavKakaoSearchFragment()
        else -> throw IllegalStateException("Unexpected position: $position")
    }

    override fun getCount(): Int {
        return 3
    }

}