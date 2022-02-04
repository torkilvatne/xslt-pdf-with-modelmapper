package com.torkilvatne.xsltpdfwithmodelmapper.services

import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service
import com.torkilvatne.xsltpdfwithmodelmapper.models.Customer
import com.torkilvatne.xsltpdfwithmodelmapper.models.CustomerWithOrders

@Service
class ShoppingApiService(
    private val modelMapper: ModelMapper,
    private val customersService: CustomersService
) {
    fun handleGetCustomerById(customerId: Int, lan: String): CustomerWithOrders? {
        return when (val customer: Customer? = customersService.getCustomer(customerId)) {
            null -> null
            else -> modelMapper.map(customer, CustomerWithOrders::class.java)
        }
    }

    fun handleGetAllCustomers(): List<CustomerWithOrders> {
        return customersService.customerList.values.map { modelMapper.map(it, CustomerWithOrders::class.java) }
    }
}