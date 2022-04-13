package com.mobiletandil.harrypotterwiki.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobiletandil.domain.entity.Spell
import com.mobiletandil.harrypotterwiki.R
import com.mobiletandil.harrypotterwiki.databinding.SpellsListBinding

class SpellsAdapter(private val spells: List<Spell>) :
    RecyclerView.Adapter<SpellsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.spells_list, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(spells[position])
    }

    override fun getItemCount() = spells.size

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(spell: Spell) {
            with(SpellsListBinding.bind(itemView)) {
                spellsName.text = spell.name
            }
        }
    }
}
