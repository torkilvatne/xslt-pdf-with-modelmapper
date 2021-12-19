package com.torkilvatne.xsltpdfwithmodelmapper.services

import org.springframework.stereotype.Service
import com.torkilvatne.xsltpdfwithmodelmapper.models.Address
import com.torkilvatne.xsltpdfwithmodelmapper.models.Customer
import com.torkilvatne.xsltpdfwithmodelmapper.models.Name
import com.torkilvatne.xsltpdfwithmodelmapper.models.Order

@Service
class CustomersService {
    fun getCustomer(id: Int): Customer? {
        return customerList[id]
    }

    fun getOrder(id: Int): Order? {
        return orderList.find { it.customerId == id }
    }

    fun getOrders(id: Int): List<Order> {
        return orderList.filter { it.customerId == id }
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
    )
}