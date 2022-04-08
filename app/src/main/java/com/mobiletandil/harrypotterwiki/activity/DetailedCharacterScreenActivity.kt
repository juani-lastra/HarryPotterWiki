package com.mobiletandil.harrypotterwiki.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.mobiletandil.domain.entity.HouseHead
import com.mobiletandil.domain.entity.Spells
import com.mobiletandil.domain.entity.Wizards
import com.mobiletandil.harrypotterwiki.R
import com.mobiletandil.harrypotterwiki.databinding.ActivityDetailedCharacterScreenBinding
import com.mobiletandil.harrypotterwiki.utils.Constants
import com.mobiletandil.harrypotterwiki.utils.Event
import com.mobiletandil.harrypotterwiki.viewmodel.DetailedCharacterData
import com.mobiletandil.harrypotterwiki.viewmodel.DetailedCharacterScreenViewModel
import com.mobiletandil.harrypotterwiki.viewmodel.DetailedCharacterStatus
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailedCharacterScreenActivity : AppCompatActivity() {
    private val viewModel by viewModel<DetailedCharacterScreenViewModel>()
    private lateinit var binding: ActivityDetailedCharacterScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailedCharacterScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.liveData().observe(this, updateUIObserver)
        viewModel.initUI(
            intent.extras?.get(HEAD) as HouseHead?,
            intent.extras?.get(WIZARD) as Wizards?,
            intent.extras?.get(SPELL) as Spells?
        )
    }

    private val updateUIObserver = Observer<Event<DetailedCharacterData>> { event ->
        val eventData = event.getContentIfNotHandled()
        when (eventData?.statusType) {
            DetailedCharacterStatus.INIT_UI_HEAD -> {
                initUIHead(eventData.headData)
            }
            DetailedCharacterStatus.INIT_UI_WIZARD -> {
                initUIWizard(eventData.wizardData)
            }
            DetailedCharacterStatus.INIT_UI_SPELL -> {
                initUISpell(eventData.spellData)
            }
            DetailedCharacterStatus.EMPTY_STATE -> {
                setEmptyState()
            }
        }
    }

    private fun initUIHead(headData: HouseHead?) {
        with(binding) {
            toolbar.text = getString(R.string.toolbar_title_head)
            fieldOne.visibility = View.VISIBLE
            fieldTwo.visibility = View.VISIBLE
            fieldOne.text = getString(R.string.wizards_name_placeholder, headData?.firstName)
            fieldTwo.text = getString(R.string.wizards_lastname_placeholder, headData?.lastName)
        }
    }

    private fun initUIWizard(wizardData: Wizards?) {
        with(binding) {
            toolbar.text = getString(R.string.toolbar_title_wizard)
            fieldTwo.visibility = View.VISIBLE
            fieldThree.visibility = View.VISIBLE
            if (wizardData?.firstName.isNullOrEmpty()) {
                fieldTwo.text = getString(R.string.wizards_name_placeholder, wizardData?.lastName)
            } else {
                fieldOne.visibility = View.VISIBLE
                fieldOne.text = getString(R.string.wizards_name_placeholder, wizardData?.firstName)
                fieldTwo.text = getString(R.string.wizards_lastname_placeholder, wizardData?.lastName)
            }
            fieldThree.text = wizardData?.elixirs?.joinToString(
                prefix = Constants.PREFIX_ELIXIRS,
                separator = Constants.COMMA_SEPARATOR
            ) { it.name.toString() }
        }
    }

    private fun initUISpell(spellData: Spells?) {
        with(binding) {
            toolbar.text = getString(R.string.toolbar_title_spell)
            fieldOne.visibility = View.VISIBLE
            fieldTwo.visibility = View.VISIBLE
            fieldThree.visibility = View.VISIBLE
            fieldFour.visibility = View.VISIBLE
            fieldFive.visibility = View.VISIBLE
            fieldSix.visibility = View.VISIBLE
            fieldSeven.visibility = View.VISIBLE

            fieldOne.text = if (spellData?.name.isNullOrEmpty()) {
                getString(R.string.spell_name_placeholder, getString(R.string.spell_anonymous_field))
            } else {
                getString(R.string.spell_name_placeholder, spellData?.name)
            }
            fieldTwo.text = if (spellData?.incantation.isNullOrEmpty()) {
                getString(R.string.spell_incantation_placeholder, getString(R.string.spell_anonymous_field))
            } else {
                getString(R.string.spell_incantation_placeholder, spellData?.incantation)
            }
            fieldThree.text = if (spellData?.effect.isNullOrEmpty()) {
                getString(R.string.spell_effect_placeholder, getString(R.string.spell_anonymous_field))
            } else {
                getString(R.string.spell_effect_placeholder, spellData?.effect)
            }
            fieldFour.text = if (spellData?.type.isNullOrEmpty()) {
                getString(R.string.spell_type_placeholder, getString(R.string.spell_anonymous_field))
            } else {
                getString(R.string.spell_type_placeholder, spellData?.type)
            }
            fieldSix.text = if (spellData?.light.isNullOrEmpty()) {
                getString(R.string.spell_light_placeholder, getString(R.string.spell_anonymous_field))
            } else {
                getString(R.string.spell_light_placeholder, spellData?.light)
            }
            fieldSeven.text = if (spellData?.creator.isNullOrEmpty()) {
                getString(R.string.spell_creator_placeholder, getString(R.string.spell_anonymous_field))
            } else {
                getString(R.string.spell_creator_placeholder, spellData?.creator)
            }
            fieldFive.text = if (spellData?.canBeVerbal == true) {
                getString(R.string.text_is_verbal)
            } else {
                getString(R.string.text_is_not_verbal)
            }
        }
    }

    private fun setEmptyState() {
        binding.fieldDescriptionLayout.visibility = View.GONE
        binding.noConnectionText.visibility = View.VISIBLE
    }

    companion object {
        private const val HEAD = "HEAD"
        private const val WIZARD = "WIZARD"
        private const val SPELL = "SPELL"
        fun getIntent(context: Context, head: HouseHead? = null, wizard: Wizards? = null, spell: Spells? = null) =
            Intent(context, DetailedCharacterScreenActivity::class.java).apply {
                putExtra(HEAD, head)
                putExtra(WIZARD, wizard)
                putExtra(SPELL, spell)
            }
    }
}
