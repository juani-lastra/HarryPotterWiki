package com.mobiletandil.harrypotterwiki.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.mobiletandil.harrypotterwiki.databinding.ActivityMainBinding
import com.mobiletandil.harrypotterwiki.utils.Event
import com.mobiletandil.harrypotterwiki.utils.Houses.GRYFFINDOR_HOUSE
import com.mobiletandil.harrypotterwiki.utils.Houses.HUFFLEPUFF_HOUSE
import com.mobiletandil.harrypotterwiki.utils.Houses.RAVENCLAW_HOUSE
import com.mobiletandil.harrypotterwiki.utils.Houses.SLYTHERIN_HOUSE
import com.mobiletandil.harrypotterwiki.viewmodel.MainActivityData
import com.mobiletandil.harrypotterwiki.viewmodel.MainActivityStatus.GO_TO_HOUSES_ACTIVITY
import com.mobiletandil.harrypotterwiki.viewmodel.MainActivityStatus.GO_TO_SPELLS_ACTIVITY
import com.mobiletandil.harrypotterwiki.viewmodel.MainActivityStatus.GO_TO_WIZARDS_ACTIVITY
import com.mobiletandil.harrypotterwiki.viewmodel.MainActivityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModel<MainActivityViewModel>()
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListeners()
        viewModel.liveData().observe(this, updateUIObserver)
    }

    private val updateUIObserver = Observer<Event<MainActivityData>> { event ->
        val eventData = event.getContentIfNotHandled()
        when (eventData?.statusType) {
            GO_TO_HOUSES_ACTIVITY -> startActivity(HousesActivity.getIntent(this, eventData.house))
            GO_TO_SPELLS_ACTIVITY -> startActivity(SpellsActivity.getIntent(this))
            GO_TO_WIZARDS_ACTIVITY -> startActivity(WizardsActivity.getIntent(this))
        }
    }

    private fun setListeners() {
        with(binding) {
            buttonGryffindor.setOnClickListener { with(viewModel) { goToHousesActivity(GRYFFINDOR_HOUSE) } }
            buttonHufflepuff.setOnClickListener { viewModel.goToHousesActivity(HUFFLEPUFF_HOUSE) }
            buttonRavenclaw.setOnClickListener { viewModel.goToHousesActivity(RAVENCLAW_HOUSE) }
            buttonSlytherin.setOnClickListener { viewModel.goToHousesActivity(SLYTHERIN_HOUSE) }
            buttonSpells.setOnClickListener { viewModel.goToSpellsActivity() }
            buttonWizards.setOnClickListener { viewModel.goToWizardsActivity() }
        }
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, MainActivity::class.java)
    }
}
