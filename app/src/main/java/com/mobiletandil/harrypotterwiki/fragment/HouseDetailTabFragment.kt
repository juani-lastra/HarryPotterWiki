package com.mobiletandil.harrypotterwiki.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.mobiletandil.domain.entity.House
import com.mobiletandil.harrypotterwiki.R
import com.mobiletandil.harrypotterwiki.databinding.HouseDetailTabFragmentBinding
import com.mobiletandil.harrypotterwiki.utils.Constants.HOUSE_DATA_KEY
import com.mobiletandil.harrypotterwiki.utils.Event
import com.mobiletandil.harrypotterwiki.viewmodel.HouseDetailData
import com.mobiletandil.harrypotterwiki.viewmodel.HouseDetailStatus.INIT_UI
import com.mobiletandil.harrypotterwiki.viewmodel.HouseDetailTabFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HouseDetailTabFragment : Fragment() {
    private lateinit var binding: HouseDetailTabFragmentBinding
    private val viewModel by viewModel<HouseDetailTabFragmentViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = HouseDetailTabFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.liveData().observe(viewLifecycleOwner, updateUIObserver)
        viewModel.initUI(arguments?.get(HOUSE_DATA_KEY) as House)
    }

    private val updateUIObserver = Observer<Event<HouseDetailData>> { event ->
        val eventData = event.getContentIfNotHandled()
        when (eventData?.statusType) {
            INIT_UI -> {
                with(binding) {
                    eventData.houseData?.let {
                        houseTitle.text = it.name
                        houseFounder.text = getString(R.string.house_founder_description, it.founder)
                        houseColors.text = getString(R.string.house_colors_description, it.houseColours)
                        houseAnimal.text = getString(R.string.house_animal_description, it.animal)
                        houseElement.text = getString(R.string.house_element_description, it.element)
                        houseGhost.text = getString(R.string.house_ghost_description, it.ghost)
                        houseCommonRoom.text = getString(R.string.house_common_room_description, it.commonRoom)
                    }
                }
            }
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
