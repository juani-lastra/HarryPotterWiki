package com.mobiletandil.harrypotterwiki.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobiletandil.domain.entity.Wizards
import com.mobiletandil.harrypotterwiki.R
import com.mobiletandil.harrypotterwiki.databinding.WizardsListBinding
import com.mobiletandil.harrypotterwiki.utils.Constants.COMMA_SEPARATOR
import com.mobiletandil.harrypotterwiki.utils.Constants.PREFIX_ELIXIRS

class WizardsAdapter(private val wizards: List<Wizards>) :
    RecyclerView.Adapter<WizardsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.wizards_list, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(wizards[position])
    }

    override fun getItemCount() = wizards.size

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(wizards: Wizards) {
            with(WizardsListBinding.bind(itemView)) {
                if (wizards.firstName.isNullOrBlank()) {
                    wizardName.visibility = View.GONE
                } else {
                    wizardName.text = itemView.context.getString(R.string.wizards_name_placeholder, wizards.firstName)
                }
                if (wizards.lastName.isNullOrBlank()) {
                    wizardLastName.visibility = View.GONE
                } else {
                    wizardLastName.text = itemView.context.getString(R.string.wizards_lastname_placeholder, wizards.lastName)
                }
                wizardElixirs.text =
                    wizards.elixirs?.joinToString(prefix = PREFIX_ELIXIRS, separator = COMMA_SEPARATOR) { it.name.toString() }
            }
        }
    }
}
