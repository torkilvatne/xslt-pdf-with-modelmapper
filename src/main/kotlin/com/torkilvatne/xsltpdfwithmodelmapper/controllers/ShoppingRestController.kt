package com.torkilvatne.xsltpdfwithmodelmapper.controllers

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import com.torkilvatne.xsltpdfwithmodelmapper.services.ShoppingService

@RestController
@RequestMapping("/api")
class ShoppingRestController(
    private val shoppingService: ShoppingService,
) {
    @GetMapping(
        value = ["/getorder/{customerId}/{language}/{format}"],
        produces = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE]
    )
    fun getCustomerById(
        @PathVariable customerId: Int,
        @PathVariable language: String,
        @PathVariable format: String,
    ): ResponseEntity<String> {
        return when (format) {
            "pdf" -> ResponseEntity.ok(shoppingService.createPdfFromCustomer(customerId, language))
            "xml" -> ResponseEntity.ok(shoppingService.createXmlFromCustomer(customerId, language))
            else -> ResponseEntity.ok("Please define format")
        }
    }
}