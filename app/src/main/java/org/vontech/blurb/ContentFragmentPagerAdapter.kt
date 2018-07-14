package org.vontech.blurb

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * The adapter that controls the scrolling of the ViewPager
 */
class ContentFragmentPagerAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> NewSayingFragment.newInstance()
            1 -> CurrentSayingFragment.newInstance()
            else -> SayingListFragment.newInstance()
        }
    }

    override fun getCount(): Int {
        return 3
    }

}