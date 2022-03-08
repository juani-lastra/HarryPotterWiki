package com.mobiletandil.harrypotterwiki.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mobiletandil.harrypotterwiki.adapter.ViewPagerAdapter
import com.mobiletandil.harrypotterwiki.databinding.HousesActivityBinding
import com.mobiletandil.harrypotterwiki.utils.Constants.TAB_NAME_DETAILS
import com.mobiletandil.harrypotterwiki.utils.Constants.TAB_NAME_HEADS
import com.mobiletandil.harrypotterwiki.utils.Constants.TAB_NAME_TRAITS
import com.mobiletandil.harrypotterwiki.utils.Houses

class HousesActivity : AppCompatActivity() {
    private lateinit var binding: HousesActivityBinding
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HousesActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewPager = binding.viewPager2
        tabLayout = binding.tabLayout
        setTabs()
    }

    private fun setTabs() {
        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = adapter

        val tabsArray = arrayOf(
            TAB_NAME_DETAILS,
            TAB_NAME_HEADS,
            TAB_NAME_TRAITS
        )

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabsArray[position]
        }.attach()
    }

    companion object {
        private const val HOUSE = "HOUSE"
        fun getIntent(context: Context, house: Houses) =
            Intent(context, HousesActivity::class.java).apply {
                putExtra(HOUSE, house)
            }
    }
}
