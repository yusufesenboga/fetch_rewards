package com.example.fetch_rewards_exercise.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fetch_rewards_exercise.R
import com.example.fetch_rewards_exercise.adapter.RecyclerViewAdapter
import com.example.fetch_rewards_exercise.model.RewardsListItem

class CategoryActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerViewAdapter
    private var categoryItems: ArrayList<RewardsListItem>? = null
    private var categoryId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        categoryId = intent.getIntExtra("categoryId", 0)
        categoryItems = intent.getParcelableArrayListExtra("categoryItems")

        categoryItems?.sortBy { it.name }
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        recyclerView = findViewById(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(this)
        categoryItems?.let {
            adapter = RecyclerViewAdapter(it)
            recyclerView.adapter = adapter
        }
    }
}
