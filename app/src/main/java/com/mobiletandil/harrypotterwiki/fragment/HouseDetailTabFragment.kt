package com.mobiletandil.harrypotterwiki.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.mobiletandil.domain.entity.House
import com.mobiletandil.harrypotterwiki.databinding.HouseDetailTabFragmentBinding
import com.mobiletandil.harrypotterwiki.utils.Constants.HOUSE_DATA_KEY
import com.mobiletandil.harrypotterwiki.utils.Event
import com.mobiletandil.harrypotterwiki.viewmodel.HouseDetailData
import com.mobiletandil.harrypotterwiki.viewmodel.HouseDetailStatus.INIT_UI
import com.mobiletandil.harrypotterwiki.viewmodel.HouseDetailTabFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HouseDetailTabFragment : Fragment() {
    private lateinit var binding: HouseDetailTabFragmentBinding
    private lateinit var house: House
    private val viewModel by viewModel<HouseDetailTabFragmentViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = HouseDetailTabFragmentBinding.inflate(layoutInflater)
        house = arguments?.get(HOUSE_DATA_KEY) as House
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.liveData().observe(viewLifecycleOwner, updateUIObserver)
        viewModel.initUI()
    }

    private val updateUIObserver = Observer<Event<HouseDetailData>> { event ->
        val eventData = event.getContentIfNotHandled()
        when (eventData?.statusType) {
            INIT_UI -> binding.houseTitle.text = house.name
        }
    }

    companion object {
        fun createInstance(house: House): HouseDetailTabFragment {
            val fragment = HouseDetailTabFragment()
            val args = Bundle()
            args.putSerializable(HOUSE_DATA_KEY, house)
            fragment.arguments = args
            return fragment
        }
    }
}
