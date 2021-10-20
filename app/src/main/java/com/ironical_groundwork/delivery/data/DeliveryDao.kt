package com.ironical_groundwork.delivery.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ironical_groundwork.delivery.model.Order
import com.ironical_groundwork.delivery.model.Product
import com.ironical_groundwork.delivery.model.Route

@Dao
interface DeliveryDao {

    // Route

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRoute(route: Route)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRoute(routeList: List<Route>)

    @Update
    suspend fun updateRoute(route: Route)

    @Update
    suspend fun updateAllRoute(routeList: List<Route>)

    @Delete
    suspend fun deleteRoute(route: Route)

    @Query("DELETE FROM route_table")
    suspend fun deleteAllRoute()

    @Query("DELETE FROM route_table WHERE id NOT IN (:idList)")
    suspend fun deleteRouteNotIn(idList: List<Int>)

    @Query("SELECT * FROM route_table ORDER BY id ASC")
    fun readAllRoute(): LiveData<List<Route>>

    // Order

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addOrder(order: Order)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllOrder(orderList: List<Order>)

    @Update
    suspend fun updateOrder(order: Order)

    @Update
    suspend fun updateAllOrder(orderList: List<Order>)

    @Delete
    suspend fun deleteOrder(order: Order)

    @Query("DELETE FROM order_table")
    suspend fun deleteAllOrder()

    @Query("DELETE FROM order_table WHERE id NOT IN (:idList)")
    suspend fun deleteOrderNotIn(idList: List<Int>)

    @Query("SELECT * FROM order_table ORDER BY id ASC")
    fun readAllOrder(): LiveData<List<Order>>

    // Product

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProduct(product: Product)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllProduct(productList: List<Product>)

    @Update
    suspend fun updateProduct(product: Product)

    @Update
    suspend fun updateAllProduct(productList: List<Product>)

    @Delete
    suspend fun deleteProduct(product: Product)

    @Query("DELETE FROM product_table")
    suspend fun deleteAllProduct()

    @Query("DELETE FROM product_table WHERE id NOT IN (:idList)")
    suspend fun deleteProductNotIn(idList: List<Int>)

    @Query("SELECT * FROM product_table ORDER BY id ASC")
    fun readAllProduct(): LiveData<List<Product>>
}