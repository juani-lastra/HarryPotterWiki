package com.mobiletandil.harrypotterwiki.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobiletandil.domain.entity.Wizards
import com.mobiletandil.harrypotterwiki.R
import com.mobiletandil.harrypotterwiki.adapter.WizardsAdapter
import com.mobiletandil.harrypotterwiki.databinding.WizardsActivityBinding
import com.mobiletandil.harrypotterwiki.listeners.WizardOnClickListener
import com.mobiletandil.harrypotterwiki.utils.Event
import com.mobiletandil.harrypotterwiki.viewmodel.WizardsActivityData
import com.mobiletandil.harrypotterwiki.viewmodel.WizardsActivityStatus.EMPTY_STATE
import com.mobiletandil.harrypotterwiki.viewmodel.WizardsActivityStatus.ERROR_STATE
import com.mobiletandil.harrypotterwiki.viewmodel.WizardsActivityStatus.GO_TO_DETAILED_SCREEN
import com.mobiletandil.harrypotterwiki.viewmodel.WizardsActivityStatus.INIT_UI
import com.mobiletandil.harrypotterwiki.viewmodel.WizardsActivityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class WizardsActivity : AppCompatActivity(), WizardOnClickListener {
    private lateinit var binding: WizardsActivityBinding
    private val viewModel by viewModel<WizardsActivityViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = WizardsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.liveData().observe(this, updateUIObserver)
        viewModel.initUI()
    }

    private val updateUIObserver = Observer<Event<WizardsActivityData>> { event ->
        val eventData = event.getContentIfNotHandled()
        when (eventData?.statusType) {
            INIT_UI -> {
                with(binding.recyclerViewWizardsList) {
                    layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    adapter = eventData.listOfWizards?.let { WizardsAdapter(it, this@WizardsActivity) }
                }
            }
            EMPTY_STATE -> setEmptyState()
            ERROR_STATE -> setErrorState()
            GO_TO_DETAILED_SCREEN -> startActivity(DetailedCharacterScreenActivity.getIntent(this, wizard = eventData.wizard))
        }
    }

    override fun WizardOnClickListener(wizard: Wizards) {
        viewModel.goToDetailedScreen(wizard)
    }

    private fun setEmptyState() {
        binding.recyclerViewWizardsList.visibility = View.GONE
        binding.wizardsActivityNoConnectionText.visibility = View.VISIBLE
    }

    private fun setErrorState() {
        binding.recyclerViewWizardsList.visibility = View.GONE
        binding.wizardsActivityNoConnectionText.text = getString(R.string.show_error_msg_text)
        binding.wizardsActivityNoConnectionText.visibility = View.VISIBLE
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, WizardsActivity::class.java)
    }
}
