package com.torkilvatne.xsltpdfwithmodelmapper.models.converters

import com.torkilvatne.xsltpdfwithmodelmapper.models.Order
import com.torkilvatne.xsltpdfwithmodelmapper.models.OrderWithProduct
import com.torkilvatne.xsltpdfwithmodelmapper.services.CustomersService
import org.modelmapper.Converter
import org.modelmapper.spi.MappingContext
import org.springframework.stereotype.Component

@Component
class OrderWithProductsConverter(
    private val customersService: CustomersService
) : Converter<Order, OrderWithProduct> {
    override fun convert(context: MappingContext<Order, OrderWithProduct>): OrderWithProduct {
        val source: Order = context.source
        return OrderWithProduct(source.customerId, customersService.getProduct(source.productId))
    }
}