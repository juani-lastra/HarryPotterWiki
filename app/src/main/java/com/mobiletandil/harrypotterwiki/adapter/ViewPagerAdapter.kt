package com.mobiletandil.harrypotterwiki.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mobiletandil.domain.entity.House
import com.mobiletandil.harrypotterwiki.fragment.HouseDetailTabFragment
import com.mobiletandil.harrypotterwiki.fragment.HouseHeadsTabFragment
import com.mobiletandil.harrypotterwiki.fragment.HouseTraitsTabFragment
import com.mobiletandil.harrypotterwiki.utils.Constants.NUM_TABS

class ViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val house: House
) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            FIRST_POSITION -> return HouseDetailTabFragment.createInstance(house)
            SECOND_POSITION -> return HouseHeadsTabFragment.createInstance(house)
        }
        return HouseTraitsTabFragment.createInstance(house)
    }

    companion object {
        const val FIRST_POSITION = 0
        const val SECOND_POSITION = 1
    }
}
