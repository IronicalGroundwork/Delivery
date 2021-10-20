package com.ironical_groundwork.delivery.model

data class RouteResponse(
    val routeList: List<Route>,
    val orderList: List<Order>,
    val productList: List<Product>
)
