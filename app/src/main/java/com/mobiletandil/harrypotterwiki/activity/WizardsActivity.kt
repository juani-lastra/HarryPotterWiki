package com.mobiletandil.harrypotterwiki.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mobiletandil.harrypotterwiki.databinding.WizardsActivityBinding

class WizardsActivity : AppCompatActivity() {
    private lateinit var binding: WizardsActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = WizardsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, WizardsActivity::class.java)
    }
}
