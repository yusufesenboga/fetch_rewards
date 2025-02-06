package com.example.fetch_rewards_exercise.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fetch_rewards_exercise.R
import com.example.fetch_rewards_exercise.model.RewardsListItem

class RecyclerViewAdapter(val rewardsList: List<RewardsListItem>) : RecyclerView.Adapter<RecyclerViewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewViewHolder =
        RecyclerViewViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_reward_list, parent, false)
        )

    override fun getItemCount(): Int = rewardsList.size

    override fun onBindViewHolder(holder: RecyclerViewViewHolder, position: Int) {
        if (!rewardsList[position].name.isNullOrBlank()) {
            holder.apply {
                itemIdText.text = String.format("ID: %d", rewardsList[position].id)
                itemListIdText.text = String.format("Group Id: %d", rewardsList[position].listId)
                itemNameTextView.text = String.format("Name: %s", rewardsList[position].name)
            }
        }
    }
}

class RecyclerViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val itemIdText: TextView = itemView.findViewById(R.id.item_id)
    val itemListIdText: TextView = itemView.findViewById(R.id.list_id)
    val itemNameTextView: TextView = itemView.findViewById(R.id.item_name)
}
