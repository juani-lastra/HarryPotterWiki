package com.mobiletandil.harrypotterwiki.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobiletandil.domain.entity.Wizards
import com.mobiletandil.harrypotterwiki.R
import com.mobiletandil.harrypotterwiki.databinding.WizardsListBinding
import com.mobiletandil.harrypotterwiki.listeners.WizardOnClickListener

class WizardsAdapter(private val wizards: List<Wizards>, private val wizardOnClickListener: WizardOnClickListener) :
    RecyclerView.Adapter<WizardsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.wizards_list, parent, false), wizardOnClickListener
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(wizards[position])
    }

    override fun getItemCount() = wizards.size

    class ViewHolder(itemView: View, private val wizardOnClickListener: WizardOnClickListener) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(wizard: Wizards) {
            with(WizardsListBinding.bind(itemView)) {
                itemView.setOnClickListener { wizardOnClickListener.WizardOnClickListener(wizard) }
                when {
                    wizard.firstName.isNullOrEmpty() -> wizardName.text = wizard.lastName
                    else ->
                        wizardName.text =
                            itemView.context.getString(R.string.wizards_only_name_placeholder, wizard.firstName, wizard.lastName)
                }
            }
        }
    }
}
