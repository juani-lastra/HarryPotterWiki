package com.mobiletandil.harrypotterwiki.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobiletandil.domain.entity.House
import com.mobiletandil.harrypotterwiki.adapter.TraitsDetailsAdapter
import com.mobiletandil.harrypotterwiki.databinding.HouseTraitsTabFragmentBinding
import com.mobiletandil.harrypotterwiki.utils.Constants.HOUSE_DATA_KEY
import com.mobiletandil.harrypotterwiki.utils.Event
import com.mobiletandil.harrypotterwiki.viewmodel.HouseTraitsData
import com.mobiletandil.harrypotterwiki.viewmodel.HouseTraitsStatus
import com.mobiletandil.harrypotterwiki.viewmodel.HouseTraitsTabFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HouseTraitsTabFragment : Fragment() {
    private lateinit var binding: HouseTraitsTabFragmentBinding
    private val viewModel by viewModel<HouseTraitsTabFragmentViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = HouseTraitsTabFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.liveData().observe(viewLifecycleOwner, updateUIObserver)
        viewModel.initUI(arguments?.get(HOUSE_DATA_KEY) as House)
    }

    private val updateUIObserver = Observer<Event<HouseTraitsData>> { event ->
        val eventData = event.getContentIfNotHandled()
        when (eventData?.statusType) {
            HouseTraitsStatus.INIT_UI -> {
                binding.recyclerViewSearchList.apply {
                    layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    adapter = eventData.houseData?.traits?.let { TraitsDetailsAdapter(it) }
                }
            }
        }
    }

    companion object {
        fun createInstance(house: House): HouseTraitsTabFragment {
            val fragment = HouseTraitsTabFragment()
            val args = Bundle()
            args.putSerializable(HOUSE_DATA_KEY, house)
            fragment.arguments = args
            return fragment
        }
    }
}
