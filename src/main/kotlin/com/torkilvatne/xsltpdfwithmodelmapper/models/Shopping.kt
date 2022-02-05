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
    val customers: Collection<CustomerWithOrders>,
    val qrCode: QrCode,
)

@NoArg
data class CustomerWithOrders(
    val customerId: Int,
    val name: Name,
    val address: Address,
    @get:JacksonXmlElementWrapper(localName = "orders")
    @get:JacksonXmlProperty(localName = "order")
    val orderList: List<OrderWithProduct>,
)

@NoArg
data class Order(
    val customerId: Int,
    val productId: Int,
)

@NoArg
data class OrderWithProduct(
    val customerId: Int,
    val product: Product,
)

@NoArg
data class Product(
    val productId: Int,
    val price: Int,
)

@NoArg
data class Customer(
    val customerId: Int,
    val name: Name,
    val address: Address,
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

@NoArg
data class QrCode(
    val base64: String,
    val width: Int,
    val height: Int,
    val format: String
) {
    @JacksonXmlProperty
    fun url(): String {
        return "url(data:image/$format;base64,$base64)"
    }
}