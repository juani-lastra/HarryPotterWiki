package com.mobiletandil.harrypotterwiki.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobiletandil.domain.entity.Spells
import com.mobiletandil.harrypotterwiki.R
import com.mobiletandil.harrypotterwiki.databinding.SpellsListBinding
import com.mobiletandil.harrypotterwiki.listeners.SpellOnClickListener

class SpellsAdapter(private val spells: List<Spells>, private val spellOnClickListener: SpellOnClickListener) :
    RecyclerView.Adapter<SpellsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.spells_list, parent, false), spellOnClickListener
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(spells[position])
    }

    override fun getItemCount() = spells.size

    class ViewHolder(itemView: View, private val spellOnClickListener: SpellOnClickListener) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(spell: Spells) {
            with(SpellsListBinding.bind(itemView)) {
                itemView.setOnClickListener { spellOnClickListener.SpellOnClickListener(spell) }
                spellsName.text = spell.name
            }
        }
    }
}
