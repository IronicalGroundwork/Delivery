package com.ironical_groundwork.delivery.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ironical_groundwork.delivery.model.District
import com.ironical_groundwork.delivery.model.Order
import com.ironical_groundwork.delivery.model.Product
import com.ironical_groundwork.delivery.model.Route
import kotlinx.coroutines.flow.Flow

@Dao
interface DeliveryDao {

    // Route

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRoute(routeList: List<Route>)

    @Update
    suspend fun updateRoute(route: Route)

    @Query("DELETE FROM route_table WHERE id NOT IN (:idList)")
    suspend fun deleteRouteNotIn(idList: List<Int>)

    @Query("SELECT * FROM route_table ORDER BY id ASC")
    fun readAllRoute(): LiveData<List<Route>>

    // Order

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllOrder(orderList: List<Order>)

    @Update
    suspend fun updateOrder(order: Order)

    @Query("DELETE FROM order_table WHERE id NOT IN (:idList)")
    suspend fun deleteOrderNotIn(idList: List<Int>)

    @Query("SELECT * FROM order_table ORDER BY id ASC")
    fun readAllOrder(): LiveData<List<Order>>

    // Product

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllProduct(productList: List<Product>)

    @Query("DELETE FROM product_table WHERE id NOT IN (:idList)")
    suspend fun deleteProductNotIn(idList: List<Int>)

    @Query("SELECT * FROM product_table ORDER BY id ASC")
    fun readAllProduct(): LiveData<List<Product>>

    // District

    @Query("SELECT district AS name, COUNT(district) AS order_count FROM order_table WHERE status!=2 AND route_id=:routeId GROUP BY district ORDER BY district ASC")
    fun readDistrict(routeId: Int): Flow<List<District>>

}