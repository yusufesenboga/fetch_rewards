package com.example.fetch_rewards_exercise

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fetch_rewards_exercise.adapter.RecyclerViewAdapterForCategories
import com.example.fetch_rewards_exercise.model.RewardsListItem
import com.example.fetch_rewards_exercise.network.GetDataService
import com.example.fetch_rewards_exercise.network.RetrofitClient
import com.example.fetch_rewards_exercise.views.CategoryActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: RecyclerViewAdapterForCategories
    private var list: MutableList<RewardsListItem> = mutableListOf()
    var getPhotoService: GetDataService? =
        RetrofitClient.instance?.create(GetDataService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        fetchList()
    }

    private fun fetchList() {
        getPhotoService?.getList()?.enqueue(object : Callback<List<RewardsListItem>> {
            override fun onResponse(
                call: Call<List<RewardsListItem>>,
                response: Response<List<RewardsListItem>>
            ) {
                val body = response.body()
                if (body != null) {
                    list.clear()
                    list.addAll(body.filter { !it.name.isNullOrBlank() })
                    list.sortBy { it.listId }
                    adapter = RecyclerViewAdapterForCategories(categoryList(list)) { categoryId ->
                        val filteredList = list.filter { it.listId == categoryId }.toMutableList()
                        navigateToCategory(categoryId, filteredList)
                    }
                    recycler.adapter = adapter
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<List<RewardsListItem>>, t: Throwable) {
            }
        })
    }

    private fun navigateToCategory(categoryId: Int, categoryItems: MutableList<RewardsListItem>) {
        val intent = Intent(this, CategoryActivity::class.java).apply {
            putParcelableArrayListExtra("categoryItems", ArrayList(categoryItems))
            putExtra("categoryId", categoryId)
        }
        startActivity(intent)
    }

    private fun categoryList(list: MutableList<RewardsListItem>): MutableList<Int> {
        return list.mapTo(mutableSetOf()) { it.listId }.toMutableList()
    }

    private fun setupRecyclerView() {
        recycler = findViewById(R.id.category_recycler)
        adapter = RecyclerViewAdapterForCategories(categoryList(list), {})
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(this)
    }
}
