package com.ironical_groundwork.delivery.repository

import androidx.lifecycle.LiveData
import com.ironical_groundwork.delivery.data.DeliveryDao
import com.ironical_groundwork.delivery.model.Order
import com.ironical_groundwork.delivery.model.Product
import com.ironical_groundwork.delivery.model.Route

class DataRepository(private val deliveryDao: DeliveryDao) {

    val readAllRoute: LiveData<List<Route>> = deliveryDao.readAllRoute()

    suspend fun addRoute(route: Route) {
        deliveryDao.addRoute(route)
    }

    suspend fun addAllRoute(routeList: List<Route>) {
        deliveryDao.addAllRoute(routeList)
    }

    suspend fun updateRoute(route: Route) {
        deliveryDao.updateRoute(route)
    }

    suspend fun updateAllRoute(routeList: List<Route>) {
        deliveryDao.updateAllRoute(routeList)
    }

    suspend fun deleteRoute(route: Route) {
        deliveryDao.deleteRoute(route)
    }

    suspend fun deleteRouteNotIn(idList: List<Int>) {
        deliveryDao.deleteRouteNotIn(idList)
    }

    suspend fun deleteAllRoute() {
        deliveryDao.deleteAllRoute()
    }

    suspend fun addAllOrder(orderList: List<Order>) {
        deliveryDao.addAllOrder(orderList)
    }

    suspend fun updateAllOrder(orderList: List<Order>) {
        deliveryDao.updateAllOrder(orderList)
    }

    suspend fun deleteOrderNotIn(idList: List<Int>) {
        deliveryDao.deleteOrderNotIn(idList)
    }

    suspend fun addAllProduct(productList: List<Product>) {
        deliveryDao.addAllProduct(productList)
    }

    suspend fun updateAllProduct(productList: List<Product>) {
        deliveryDao.updateAllProduct(productList)
    }

    suspend fun deleteProductNotIn(idList: List<Int>) {
        deliveryDao.deleteProductNotIn(idList)
    }
}