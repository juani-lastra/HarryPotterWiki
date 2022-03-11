package com.mobiletandil.harrypotterwiki.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mobiletandil.domain.entity.House
import com.mobiletandil.domain.utils.Houses
import com.mobiletandil.domain.utils.Houses.NONE
import com.mobiletandil.harrypotterwiki.R
import com.mobiletandil.harrypotterwiki.adapter.ViewPagerAdapter
import com.mobiletandil.harrypotterwiki.databinding.HousesActivityBinding
import com.mobiletandil.harrypotterwiki.utils.Constants.TAB_NAME_DETAILS
import com.mobiletandil.harrypotterwiki.utils.Constants.TAB_NAME_HEADS
import com.mobiletandil.harrypotterwiki.utils.Constants.TAB_NAME_TRAITS
import com.mobiletandil.harrypotterwiki.utils.Event
import com.mobiletandil.harrypotterwiki.viewmodel.HousesActivityData
import com.mobiletandil.harrypotterwiki.viewmodel.HousesActivityStatus.EMPTY_STATE
import com.mobiletandil.harrypotterwiki.viewmodel.HousesActivityStatus.ERROR_STATE
import com.mobiletandil.harrypotterwiki.viewmodel.HousesActivityStatus.SET_TABS
import com.mobiletandil.harrypotterwiki.viewmodel.HousesActivityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HousesActivity : AppCompatActivity() {
    private val viewModel by viewModel<HousesActivityViewModel>()
    private lateinit var binding: HousesActivityBinding
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private var house: Houses = NONE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HousesActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewPager = binding.viewPager2
        tabLayout = binding.tabLayout
        house = intent.extras?.get(HOUSE) as Houses
        viewModel.liveData().observe(this, updateUIObserver)
        viewModel.setTabs(house)
    }

    private val updateUIObserver = Observer<Event<HousesActivityData>> { event ->
        val eventData = event.getContentIfNotHandled()
        when (eventData?.statusType) {
            SET_TABS -> setTabs(eventData.houseData)
            EMPTY_STATE -> setEmptyState()
            ERROR_STATE -> showErrorToast()
        }
    }

    private fun setEmptyState() {
        viewPager.visibility = View.GONE
        tabLayout.visibility = View.GONE
        binding.housesActivityNoConnectionImage.visibility = View.VISIBLE
        binding.housesActivityNoConnectionText.visibility = View.VISIBLE
    }

    private fun showErrorToast() = Toast.makeText(this, getString(R.string.toast_show_error), Toast.LENGTH_SHORT).show()

    private fun setTabs(houseData: House?) {
        val adapter = houseData?.let { ViewPagerAdapter(supportFragmentManager, lifecycle, it) }
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
