package com.mobiletandil.harrypotterwiki.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mobiletandil.harrypotterwiki.databinding.SpellsActivityBinding

class SpellsActivity : AppCompatActivity() {
    private lateinit var binding: SpellsActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SpellsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, SpellsActivity::class.java)
    }
}
