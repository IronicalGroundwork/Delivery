package com.ironical_groundwork.delivery.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ironical_groundwork.delivery.model.Order
import com.ironical_groundwork.delivery.model.Product
import com.ironical_groundwork.delivery.model.Route

@Database(entities = [Route::class, Order::class, Product::class], version = 11, exportSchema = false)
abstract class DeliveryDatabase: RoomDatabase() {
    abstract fun deliveryDao(): DeliveryDao

    companion object{
        @Volatile
        private var INSTANCE: DeliveryDatabase? = null

        fun getDatabase(context: Context): DeliveryDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DeliveryDatabase::class.java,
                    "delivery_database"
                ).fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
