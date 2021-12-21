package com.torkilvatne.xsltpdfwithmodelmapper.models.converters

import org.modelmapper.Converter
import org.modelmapper.spi.MappingContext
import org.springframework.stereotype.Component
import com.torkilvatne.xsltpdfwithmodelmapper.models.Customer
import com.torkilvatne.xsltpdfwithmodelmapper.models.CustomerWithOrders
import com.torkilvatne.xsltpdfwithmodelmapper.services.CustomersService

@Component
class CustomerWithOrdersConverter(
    private val customersService: CustomersService
) : Converter<Customer, CustomerWithOrders> {
    override fun convert(context: MappingContext<Customer, CustomerWithOrders>): CustomerWithOrders {
        val source: Customer = context.source
        val customerOrders = customersService.getOrders(source.customerId)
        return CustomerWithOrders(source.customerId, source.name, source.address, customerOrders)
    }
}