package com.mobiletandil.harrypotterwiki.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.mobiletandil.domain.entity.Spell
import com.mobiletandil.harrypotterwiki.R
import com.mobiletandil.harrypotterwiki.adapter.SpellsAdapter
import com.mobiletandil.harrypotterwiki.databinding.SpellsActivityBinding
import com.mobiletandil.harrypotterwiki.utils.Event
import com.mobiletandil.harrypotterwiki.viewmodel.SpellsActivityData
import com.mobiletandil.harrypotterwiki.viewmodel.SpellsActivityStatus
import com.mobiletandil.harrypotterwiki.viewmodel.SpellsActivityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SpellsActivity : AppCompatActivity() {
    private lateinit var binding: SpellsActivityBinding
    private val viewModel by viewModel<SpellsActivityViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SpellsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.liveData().observe(this, updateUIObserver)
        viewModel.initUI()
    }

    private val updateUIObserver = Observer<Event<SpellsActivityData>> { event ->
        val eventData = event.getContentIfNotHandled()
        when (eventData?.statusType) {
            SpellsActivityStatus.INIT_UI -> initUI(eventData.listOfSpells)
            SpellsActivityStatus.EMPTY_STATE -> setEmptyState()
            SpellsActivityStatus.ERROR_STATE -> setErrorState()
        }
    }

    private fun initUI(listOfSpells: List<Spell>) {
        with(binding.recyclerViewSpellsList) {
            layoutManager = LinearLayoutManager(context, VERTICAL, false)
            adapter = SpellsAdapter(listOfSpells)
        }
    }

    private fun setEmptyState() {
        binding.recyclerViewSpellsList.visibility = View.GONE
        binding.spellsActivityNoConnectionText.visibility = View.VISIBLE
    }

    private fun setErrorState() {
        binding.recyclerViewSpellsList.visibility = View.GONE
        binding.spellsActivityNoConnectionText.text = getString(R.string.show_error_msg_text)
        binding.spellsActivityNoConnectionText.visibility = View.VISIBLE
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, SpellsActivity::class.java)
    }
}
