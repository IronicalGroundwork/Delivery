package com.ironical_groundwork.delivery.repository

import com.ironical_groundwork.delivery.api.RetrofitInstance
import com.ironical_groundwork.delivery.model.Login
import com.ironical_groundwork.delivery.model.Route
import com.ironical_groundwork.delivery.model.RouteResponse
import retrofit2.Response

class ApiRepository {

    suspend fun pushPostLogin(code: String, userId: String?, phoneName: String): Response<Login> {
        return RetrofitInstance.api.pushPostLogin(code, userId, phoneName)
    }

    suspend fun getRouteList(userId: Int): Response<RouteResponse> {
        return RetrofitInstance.api.getRouteList(userId)
    }

    suspend fun getAllRouteList(): Response<RouteResponse> {
        return RetrofitInstance.api.getAllRouteList()
    }
}