package com.ironical_groundwork.delivery.repository

import com.ironical_groundwork.delivery.api.RetrofitInstance
import com.ironical_groundwork.delivery.model.Login
import retrofit2.Response

class Repository {

    suspend fun pushPostLogin(code: String, userId: String, phoneName: String): Response<Login> {
        return RetrofitInstance.api.pushPostLogin(code, userId, phoneName)
    }
}