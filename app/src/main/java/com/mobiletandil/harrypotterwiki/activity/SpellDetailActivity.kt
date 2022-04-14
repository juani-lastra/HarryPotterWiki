package com.mobiletandil.harrypotterwiki.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.mobiletandil.domain.entity.Spell
import com.mobiletandil.harrypotterwiki.R
import com.mobiletandil.harrypotterwiki.databinding.ActivitySpellDetailBinding
import com.mobiletandil.harrypotterwiki.utils.Event
import com.mobiletandil.harrypotterwiki.viewmodel.SpellDetailActivityData
import com.mobiletandil.harrypotterwiki.viewmodel.SpellDetailActivityStatus
import com.mobiletandil.harrypotterwiki.viewmodel.SpellDetailActivityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SpellDetailActivity : AppCompatActivity() {
    private val viewModel by viewModel<SpellDetailActivityViewModel>()
    private lateinit var binding: ActivitySpellDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpellDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.liveData().observe(this, updateUIObserver)
        intent.getStringExtra(SPELL_ID)?.let { viewModel.initUI(it) }
    }

    private val updateUIObserver = Observer<Event<SpellDetailActivityData>> { event ->
        val eventData = event.getContentIfNotHandled()
        when (eventData?.statusType) {
            SpellDetailActivityStatus.INIT_UI -> {
                eventData.data?.let { initUI(it) }
            }
            SpellDetailActivityStatus.EMPTY_STATE -> setEmptyState()
            SpellDetailActivityStatus.ERROR_STATE -> setErrorState()
        }
    }

    private fun initUI(spellData: Spell) {
        with(binding) {
            toolbarText.text = spellData.name
            incantation.text =
                getString(R.string.spell_incantation, spellData.incantation ?: getString(R.string.spell_anonymous_placeholder))
            effect.text = getString(R.string.spell_effect, spellData.effect ?: getString(R.string.spell_anonymous_placeholder))
            creator.text = getString(R.string.spell_creator, spellData.creator ?: getString(R.string.spell_anonymous_placeholder))
            light.text = getString(R.string.spell_light, spellData.light ?: getString(R.string.spell_anonymous_placeholder))
            type.text = getString(R.string.spell_type, spellData.type ?: getString(R.string.spell_anonymous_placeholder))
            verbal.text = if (spellData.canBeVerbal == true) {
                getString(R.string.text_is_verbal)
            } else {
                getString(R.string.text_is_not_verbal)
            }
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
        private const val SPELL_ID = "SPELL_ID"
        fun getIntent(context: Context, spellID: String) =
            Intent(context, SpellDetailActivity::class.java).apply { putExtra(SPELL_ID, spellID) }
    }
}
