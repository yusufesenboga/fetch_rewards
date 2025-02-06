package com.example.fetch_rewards_exercise.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.fetch_rewards_exercise.R

class RecyclerViewAdapterForCategories(
    val categoriesList: MutableList<Int>,
    val onClick: (Int) -> Unit
) : RecyclerView.Adapter<RecyclerViewAdapterForCategories.RecyclerViewViewHolderForCategories>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewViewHolderForCategories =
        RecyclerViewViewHolderForCategories(
            LayoutInflater.from(parent.context).inflate(R.layout.item_categories, parent, false)
        )

    override fun onBindViewHolder(holder: RecyclerViewViewHolderForCategories, position: Int) {
        holder.categories.text = categoriesList[position].toString()
        holder.categories.setOnClickListener {
            onClick(categoriesList[position])
        }
    }

    override fun getItemCount(): Int = categoriesList.size

    class RecyclerViewViewHolderForCategories(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categories: Button = itemView.findViewById(R.id.category_button)
    }
}
