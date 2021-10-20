package com.ironical_groundwork.delivery.api

import com.ironical_groundwork.delivery.model.Login
import com.ironical_groundwork.delivery.model.Route
import com.ironical_groundwork.delivery.model.RouteResponse
import retrofit2.Response
import retrofit2.http.*

interface DeliveryApi {

    @FormUrlEncoded
    @POST("login.php?post_login")
    suspend fun pushPostLogin(
        @Field("code") code: String,
        @Field("onesignal") userId: String?,
        @Field("phone") phoneName: String
    ): Response<Login>

    @GET("api.php?")
    suspend fun getRouteList(
        @Query("userId") userId: Int
    ): Response<RouteResponse>

    @GET("api.php?get_route")
    suspend fun getAllRouteList(): Response<RouteResponse>
}