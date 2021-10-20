package com.ironical_groundwork.delivery.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ironical_groundwork.delivery.model.Login
import com.ironical_groundwork.delivery.model.Route
import com.ironical_groundwork.delivery.model.RouteResponse
import com.ironical_groundwork.delivery.repository.ApiRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class ApiViewModel(private val ApiRepository: ApiRepository): ViewModel() {
    val loginPostResponse: MutableLiveData<Response<Login>> = MutableLiveData()
    val routeResponse: MutableLiveData<Response<RouteResponse>> = MutableLiveData()

    fun pushPostLogin(code: String, userId: String?, phoneName: String) {
        viewModelScope.launch {
            val response = ApiRepository.pushPostLogin(code, userId, phoneName)
            loginPostResponse.value = response
        }
    }

    fun getRouteList(userId: Int) {
        viewModelScope.launch {
            val response = ApiRepository.getRouteList(userId)
            routeResponse.value = response
        }
    }

    fun getAllRouteList() {
        viewModelScope.launch {
            val response = ApiRepository.getAllRouteList()
            routeResponse.value = response
        }
    }

}