package com.ironical_groundwork.delivery.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "product_table",
    primaryKeys = ["id", "order_id"],
    foreignKeys = [
        ForeignKey(entity = Order::class,
            parentColumns = ["id"],
            childColumns = ["order_id"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )])
data class Product(
    val id: Int,
    val order_id: Int,
    val name: String,
    val count: Int,
    val price: String
): Parcelable