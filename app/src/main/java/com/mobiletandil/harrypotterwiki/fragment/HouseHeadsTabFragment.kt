package com.mobiletandil.harrypotterwiki.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mobiletandil.harrypotterwiki.databinding.HouseHeadsTabFragmentBinding

class HouseHeadsTabFragment : Fragment() {
    private lateinit var binding: HouseHeadsTabFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = HouseHeadsTabFragmentBinding.inflate(layoutInflater)
        return binding.root
    }
}
