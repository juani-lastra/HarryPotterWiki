package com.mobiletandil.harrypotterwiki.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mobiletandil.harrypotterwiki.databinding.HouseDetailTabFragmentBinding

class HouseDetailTabFragment : Fragment() {
    private lateinit var binding: HouseDetailTabFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = HouseDetailTabFragmentBinding.inflate(layoutInflater)
        return binding.root
    }
}
