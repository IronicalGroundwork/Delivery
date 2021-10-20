package com.ironical_groundwork.delivery.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.ironical_groundwork.delivery.data.DeliveryDatabase
import com.ironical_groundwork.delivery.model.Route
import com.ironical_groundwork.delivery.model.RouteResponse
import com.ironical_groundwork.delivery.repository.DataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DataViewModel(application: Application): AndroidViewModel(application) {

    val readAllRoute: LiveData<List<Route>>
    private val dataRepository: DataRepository

    init {
        val deliveryDao = DeliveryDatabase.getDatabase(application).deliveryDao()
        dataRepository = DataRepository(deliveryDao)
        readAllRoute = dataRepository.readAllRoute
    }

    fun addRoute(route: Route){
        viewModelScope.launch(Dispatchers.IO) {
            dataRepository.addRoute(route)
        }
    }

    fun updateAllData(data: RouteResponse ){
        viewModelScope.launch(Dispatchers.IO) {

            dataRepository.addAllRoute(data.routeList)
            dataRepository.addAllOrder(data.orderList)
            dataRepository.addAllProduct(data.productList)

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

            dataRepository.deleteRouteNotIn(idRouteList)
            dataRepository.deleteOrderNotIn(idOrderList)
            dataRepository.deleteProductNotIn(idProductList)

        }
    }

    fun updateRoute(route: Route){
        viewModelScope.launch(Dispatchers.IO) {
            dataRepository.updateRoute(route)
        }
    }


    fun deleteRoute(route: Route){
        viewModelScope.launch(Dispatchers.IO) {
            dataRepository.deleteRoute(route)
        }
    }

    fun deleteAllRoute(){
        viewModelScope.launch(Dispatchers.IO) {
            dataRepository.deleteAllRoute()
        }
    }

}