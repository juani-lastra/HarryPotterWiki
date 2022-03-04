package com.mobiletandil.harrypotterwiki.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mobiletandil.harrypotterwiki.databinding.HousesActivityBinding
import com.mobiletandil.harrypotterwiki.utils.Houses

class HousesActivity : AppCompatActivity() {
    private lateinit var binding: HousesActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HousesActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    companion object {
        private const val HOUSE = "HOUSE"
        fun getIntent(context: Context, house: Houses) =
            Intent(context, HousesActivity::class.java).apply {
                putExtra(HOUSE, house)
            }
    }
}
