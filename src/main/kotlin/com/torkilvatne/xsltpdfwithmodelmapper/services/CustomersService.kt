package com.torkilvatne.xsltpdfwithmodelmapper.services

import com.torkilvatne.xsltpdfwithmodelmapper.models.*
import org.springframework.stereotype.Service

@Service
class CustomersService {
    fun getCustomer(id: Int): Customer? {
        return customerList[id]
    }

    fun getOrder(id: Int): Order? {
        return orderList.find { it.customerId == id }
    }

    fun getOrders(cid: Int): List<Order> {
        return orderList.filter { it.customerId == cid }
    }

    fun getProduct(pid: Int): Product {
        return productList.first { it.productId == pid }
    }

    val customerList: Map<Int, Customer> = mapOf(
        123 to Customer(123, Name("Torkil", "Vatne"), Address("Fredriksborgveien", "Oslo")),
        456 to Customer(456, Name("Eric", "Berhan"), Address("Espa Boller", "Espa")),
        789 to Customer(789, Name("Ola", "Normann"), Address("Jotunheimveien", "Jotun")),
    )

    val orderList: List<Order> = listOf(
        Order(123, 111),
        Order(123, 222),
        Order(456, 222),
        Order(456, 333),
        Order(456, 333),
        Order(456, 444),
        Order(789, 444),
        Order(789, 111),
        Order(789, 222),
    )

    val productList: List<Product> = listOf(
        Product(111, 100),
        Product(222, 299),
        Product(333, 59),
        Product(444, 1999),
    )
}