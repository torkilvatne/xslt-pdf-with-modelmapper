package com.torkilvatne.xsltpdfwithmodelmapper.models.converters

import org.modelmapper.Converter
import org.modelmapper.spi.MappingContext
import org.springframework.stereotype.Component
import com.torkilvatne.xsltpdfwithmodelmapper.models.Customer
import com.torkilvatne.xsltpdfwithmodelmapper.models.CustomerWithOrders
import com.torkilvatne.xsltpdfwithmodelmapper.models.OrderWithProduct
import com.torkilvatne.xsltpdfwithmodelmapper.services.CustomersService
import org.modelmapper.ModelMapper
import org.springframework.context.annotation.Lazy

@Component
class CustomerWithOrdersConverter(
    @Lazy private val modelMapper: ModelMapper,
    private val customersService: CustomersService
) : Converter<Customer, CustomerWithOrders> {
    override fun convert(context: MappingContext<Customer, CustomerWithOrders>): CustomerWithOrders {
        val source: Customer = context.source
        val customerOrders: List<OrderWithProduct> =
            customersService.getOrders(source.customerId).map { modelMapper.map(it, OrderWithProduct::class.java) }

        return CustomerWithOrders(source.customerId, source.name, source.address, customerOrders)
    }
}