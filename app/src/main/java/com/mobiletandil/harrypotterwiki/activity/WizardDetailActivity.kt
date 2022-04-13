package com.mobiletandil.harrypotterwiki.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.mobiletandil.domain.entity.Wizard
import com.mobiletandil.domain.utils.Constants.EMPTY_STRING
import com.mobiletandil.harrypotterwiki.R
import com.mobiletandil.harrypotterwiki.databinding.ActivityWizardDetailBinding
import com.mobiletandil.harrypotterwiki.utils.Constants
import com.mobiletandil.harrypotterwiki.utils.Event
import com.mobiletandil.harrypotterwiki.viewmodel.WizardDetailActivityData
import com.mobiletandil.harrypotterwiki.viewmodel.WizardDetailActivityStatus
import com.mobiletandil.harrypotterwiki.viewmodel.WizardDetailActivityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class WizardDetailActivity : AppCompatActivity() {
    private val viewModel by viewModel<WizardDetailActivityViewModel>()
    private lateinit var binding: ActivityWizardDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWizardDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.liveData().observe(this, updateUIObserver)
        intent.getStringExtra(WIZARD_ID)?.let { viewModel.initUI(it) }
    }

    private val updateUIObserver = Observer<Event<WizardDetailActivityData>> { event ->
        val eventData = event.getContentIfNotHandled()
        when (eventData?.statusType) {
            WizardDetailActivityStatus.INIT_UI -> {
                eventData.data?.let { initUI(it) }
            }
            WizardDetailActivityStatus.EMPTY_STATE -> setEmptyState()
            WizardDetailActivityStatus.ERROR_STATE -> setErrorState()
        }
    }

    private fun initUI(wizardData: Wizard) {
        with(binding) {
            toolbarText.text = getString(
                R.string.wizards_only_name_placeholder,
                wizardData.firstName ?: EMPTY_STRING,
                wizardData.lastName
            )
            elixirs.text = wizardData.elixirs?.joinToString(
                prefix = Constants.PREFIX_ELIXIRS,
                separator = Constants.COMMA_SEPARATOR
            ) { it.name.toString() }
        }
    }

    private fun setEmptyState() {
        binding.appBarLayout.visibility = View.GONE
        binding.scrollView.visibility = View.GONE
        binding.noConnectionText.visibility = View.VISIBLE
    }

    private fun setErrorState() {
        binding.appBarLayout.visibility = View.GONE
        binding.scrollView.visibility = View.GONE
        binding.noConnectionText.visibility = View.VISIBLE
    }

    companion object {
        private const val WIZARD_ID = "WIZARD_ID"
        fun getIntent(context: Context, wizardID: String) =
            Intent(context, WizardDetailActivity::class.java).apply { putExtra(WIZARD_ID, wizardID) }
    }
}
