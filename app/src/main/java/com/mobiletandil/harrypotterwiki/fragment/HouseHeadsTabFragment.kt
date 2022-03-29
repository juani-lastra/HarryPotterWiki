package com.mobiletandil.harrypotterwiki.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobiletandil.domain.entity.House
import com.mobiletandil.harrypotterwiki.adapter.HeadsDetailsAdapter
import com.mobiletandil.harrypotterwiki.databinding.HouseHeadsTabFragmentBinding
import com.mobiletandil.harrypotterwiki.utils.Constants.HOUSE_DATA_KEY
import com.mobiletandil.harrypotterwiki.utils.Event
import com.mobiletandil.harrypotterwiki.viewmodel.HouseHeadsData
import com.mobiletandil.harrypotterwiki.viewmodel.HouseHeadsStatus
import com.mobiletandil.harrypotterwiki.viewmodel.HouseHeadsTabFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HouseHeadsTabFragment : Fragment() {
    private lateinit var binding: HouseHeadsTabFragmentBinding
    private val viewModel by viewModel<HouseHeadsTabFragmentViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = HouseHeadsTabFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.liveData().observe(viewLifecycleOwner, updateUIObserver)
        viewModel.initUI(arguments?.get(HOUSE_DATA_KEY) as House)
    }

    private val updateUIObserver = Observer<Event<HouseHeadsData>> { event ->
        val eventData = event.getContentIfNotHandled()
        when (eventData?.statusType) {
            HouseHeadsStatus.INIT_UI -> {
                binding.recyclerViewSearchList.apply {
                    layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    adapter = eventData.houseData?.heads?.let { HeadsDetailsAdapter(it) }
                }
            }
        }
    }

    companion object {
        fun createInstance(house: House): HouseHeadsTabFragment {
            val fragment = HouseHeadsTabFragment()
            val args = Bundle()
            args.putSerializable(HOUSE_DATA_KEY, house)
            fragment.arguments = args
            return fragment
        }
    }
}
