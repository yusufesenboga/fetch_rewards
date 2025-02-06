package com.example.fetch_rewards_exercise.network

import com.example.fetch_rewards_exercise.model.RewardsListItem
import retrofit2.Call
import retrofit2.http.GET

interface GetDataService {
    @GET("/hiring.json")
    fun getList(): Call<List<RewardsListItem>>
}