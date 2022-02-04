package com.torkilvatne.xsltpdfwithmodelmapper.models

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import com.torkilvatne.xsltpdfwithmodelmapper.annotations.NoArg

@NoArg
@JacksonXmlRootElement(localName = "CustomerPDF")
data class CustomerPDF(
    @get:JacksonXmlElementWrapper(localName = "customers")
    @get:JacksonXmlProperty(localName = "customer")
    var customers: Collection<CustomerWithOrders>,
)

@NoArg
data class CustomerWithOrders(
    var customerId: Int,
    var name: Name,
    var address: Address,
    @get:JacksonXmlElementWrapper(localName = "orders")
    @get:JacksonXmlProperty(localName = "order")
    var orderList: List<OrderWithProduct>,
)

@NoArg
data class Order(
    var customerId: Int,
    var productId: Int,
)

@NoArg
data class OrderWithProduct(
    var customerId: Int,
    var product: Product,
)

@NoArg
data class Product(
    var productId: Int,
    val price: Int,
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