package com.mobiletandil.harrypotterwiki.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.mobiletandil.harrypotterwiki.databinding.SplashActivityBinding
import com.mobiletandil.harrypotterwiki.utils.Event
import com.mobiletandil.harrypotterwiki.viewmodel.Data
import com.mobiletandil.harrypotterwiki.viewmodel.SplashActivityViewModel
import com.mobiletandil.harrypotterwiki.viewmodel.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: SplashActivityBinding
    private val viewModel: SplashActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SplashActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.liveData().observe(this, updateUIObserver)
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadMainActivity()
    }

    private val updateUIObserver = Observer<Event<Data>> { event ->
        val eventData = event.getContentIfNotHandled()
        when (eventData?.statusType) {
            Status.LOAD_COMPLETE -> {
                startActivity(MainActivity.getIntent(this))
                this.finish()
            }
        }
    }
}
