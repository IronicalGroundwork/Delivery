package com.ironical_groundwork.delivery.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.ironical_groundwork.delivery.data.DeliveryDatabase
import com.ironical_groundwork.delivery.model.*
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

    fun updateAllData(data: RouteResponse ){
        viewModelScope.launch(Dispatchers.IO) {
            dataRepository.updateAllData(data)
        }
    }

    fun updateRoute(route: Route){
        viewModelScope.launch(Dispatchers.IO) {
            dataRepository.updateRoute(route)
        }
    }

    fun readDistrict(routeId: Int): LiveData<List<District>>{
        return dataRepository.readDistrict(routeId).asLiveData()
    }

}
