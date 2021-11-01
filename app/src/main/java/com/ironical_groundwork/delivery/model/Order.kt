package com.ironical_groundwork.delivery.model

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
/*@Entity(tableName = "order_table",
    foreignKeys = [
        ForeignKey(entity = Route::class,
            parentColumns = ["id"],
            childColumns = ["route_id"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )])*/
@Entity(tableName = "order_table")
data class Order(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val route_id: Int,
    val number: String,
    val code: String,
    val link: String,
    @Embedded
    val address: Address,
    @Embedded
    val time: Time,
    val client: String,
    val payment: String,
    val amount: String,
    val change: String,
    val telephone: String,
    val comment: String,
    val status: Int
): Parcelable

@Parcelize
data class Address(
    val district: String,
    val direction: String,
    val city: String,
    val street: String,
    val house: String,
    val flat: Int,
    val entrance: Int
): Parcelable

@Parcelize
data class Time(
    val time_start: Int,
    val time_end: Int
): Parcelable
