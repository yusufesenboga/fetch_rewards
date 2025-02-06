package com.example.fetch_rewards_exercise.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RewardsListItem(
    val listId: Int,
    val name: String? = null,
    val id: Int
) : Parcelable
