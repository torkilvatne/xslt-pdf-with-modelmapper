package com.torkilvatne.xsltpdfwithmodelmapper.models

import com.torkilvatne.xsltpdfwithmodelmapper.annotations.NoArg

@NoArg
data class CustomerWithOrders(
    var customer: Customer,
    var orderList: List<Order>,
)

@NoArg
data class Order(
    var customerId: Int,
    var productId: Int,
)

@NoArg
data class Customer(
    var customerId: Int,
    var name: Name,
    var address: Address,
)

@NoArg
data class Name(
    val firstName: String,
    val lastName: String,
)

@NoArg
data class Address(
    val street: String,
    val city: String,
)