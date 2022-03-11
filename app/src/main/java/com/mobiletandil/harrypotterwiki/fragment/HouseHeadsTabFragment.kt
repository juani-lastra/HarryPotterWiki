package com.mobiletandil.harrypotterwiki.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mobiletandil.domain.entity.House
import com.mobiletandil.harrypotterwiki.databinding.HouseHeadsTabFragmentBinding
import com.mobiletandil.harrypotterwiki.utils.Constants.HOUSE_DATA_KEY

class HouseHeadsTabFragment : Fragment() {
    private lateinit var binding: HouseHeadsTabFragmentBinding
    private lateinit var house: House

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = HouseHeadsTabFragmentBinding.inflate(layoutInflater)
        house = arguments?.get(HOUSE_DATA_KEY) as House
        return binding.root
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
