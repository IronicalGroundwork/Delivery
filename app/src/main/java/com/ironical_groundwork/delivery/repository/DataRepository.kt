package com.ironical_groundwork.delivery.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ironical_groundwork.delivery.data.DeliveryDao
import com.ironical_groundwork.delivery.model.*
import kotlinx.coroutines.flow.Flow

class DataRepository(private val deliveryDao: DeliveryDao) {

    val readAllRoute: LiveData<List<Route>> = deliveryDao.readAllRoute()
    val readAllOrder: LiveData<List<Order>> = deliveryDao.readAllOrder()
    val readAllProduct: LiveData<List<Product>> = deliveryDao.readAllProduct()


    suspend fun updateAllData(data: RouteResponse) {
        deliveryDao.addAllRoute(data.routeList)
        deliveryDao.addAllOrder(data.orderList)
        deliveryDao.addAllProduct(data.productList)

        val idRouteList = mutableListOf<Int>()
        data.routeList.forEach {
            idRouteList.add(it.id)
        }

        val idOrderList = mutableListOf<Int>()
        data.orderList.forEach {
            idOrderList.add(it.id)
        }

        val idProductList = mutableListOf<Int>()
        data.productList.forEach {
            idProductList.add(it.id)
        }

        deliveryDao.deleteRouteNotIn(idRouteList)
        deliveryDao.deleteOrderNotIn(idOrderList)
        deliveryDao.deleteProductNotIn(idProductList)
    }

    suspend fun updateRoute(route: Route) {
       deliveryDao.updateRoute(route)
    }

    fun readDistrict(routeId: Int): Flow<List<District>> {
        return deliveryDao.readDistrict(routeId)
    }
}