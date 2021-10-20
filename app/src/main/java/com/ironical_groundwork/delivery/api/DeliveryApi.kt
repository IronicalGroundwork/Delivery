package com.ironical_groundwork.delivery.api

import com.ironical_groundwork.delivery.model.Login
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface DeliveryApi {

    @POST("login.php?post_login")
    @FormUrlEncoded
    suspend fun pushPostLogin(
        @Field("code") code: String,
        @Field("onesignal") userId: String,
        @Field("phone") phoneName: String
    ): Response<Login>

}