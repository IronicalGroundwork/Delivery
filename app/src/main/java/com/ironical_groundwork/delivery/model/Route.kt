package com.ironical_groundwork.delivery.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "route_table")
data class Route(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val number: String,
    val date: String,
    val order_count: Int,
    val status: Int
): Parcelable
