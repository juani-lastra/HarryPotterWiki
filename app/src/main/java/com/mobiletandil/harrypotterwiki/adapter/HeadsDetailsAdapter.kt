package com.mobiletandil.harrypotterwiki.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobiletandil.domain.entity.HouseHead
import com.mobiletandil.harrypotterwiki.R
import com.mobiletandil.harrypotterwiki.databinding.HeadsListBinding

class HeadsDetailsAdapter(private val head: List<HouseHead>) :
    RecyclerView.Adapter<HeadsDetailsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.heads_list, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(head[position])
    }

    override fun getItemCount() = head.size

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(head: HouseHead) {
            with(HeadsListBinding.bind(itemView)) {
                headName.text = head.firstName
                headLastName.text = head.lastName
            }
        }
    }
}
