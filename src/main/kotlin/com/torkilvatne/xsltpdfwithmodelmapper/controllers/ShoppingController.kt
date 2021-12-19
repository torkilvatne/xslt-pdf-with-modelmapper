package com.torkilvatne.xsltpdfwithmodelmapper.controllers

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import com.torkilvatne.xsltpdfwithmodelmapper.exceptions.CustomerNotFoundException
import com.torkilvatne.xsltpdfwithmodelmapper.models.CustomerWithOrders
import com.torkilvatne.xsltpdfwithmodelmapper.services.ShoppingApiService

@RestController
@RequestMapping("/api")
class ShoppingController(
    private val shoppingApiService: ShoppingApiService,
) {
    @GetMapping(
        value = ["/getorder/{customerId}/{language}"],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getCustomerById(
        @PathVariable customerId: Int,
        @PathVariable language: String,
    ): ResponseEntity<CustomerWithOrders> {
        val customer = shoppingApiService.handleGetCustomerById(customerId, language)
        if (customer === null) {
            throw CustomerNotFoundException("Customer not found!");
        }
        return ResponseEntity.ok(customer)
    }
}