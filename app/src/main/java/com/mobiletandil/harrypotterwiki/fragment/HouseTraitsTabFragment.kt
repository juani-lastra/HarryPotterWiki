package com.mobiletandil.harrypotterwiki.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mobiletandil.harrypotterwiki.databinding.HouseTraitsTabFragmentBinding

class HouseTraitsTabFragment : Fragment() {
    private lateinit var binding: HouseTraitsTabFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = HouseTraitsTabFragmentBinding.inflate(layoutInflater)
        return binding.root
    }
}
