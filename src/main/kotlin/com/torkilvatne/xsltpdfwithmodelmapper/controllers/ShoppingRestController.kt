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
        value = ["/getorder/xml/{customerId}/{language}/"],
        produces = [MediaType.APPLICATION_XML_VALUE]
    )
    fun getCustomerXmlById(
        @PathVariable customerId: Int,
        @PathVariable language: String,
    ): ResponseEntity<String> {
        return ResponseEntity.ok(shoppingService.createXmlFromCustomer(customerId, language))
    }

    @GetMapping(
        value = ["/getorder/pdf/{customerId}/{language}/"],
        produces = [MediaType.APPLICATION_PDF_VALUE]
    )
    fun getCustomerPdfById(
        @PathVariable customerId: Int,
        @PathVariable language: String,
    ): ResponseEntity<ByteArray> {
        return ResponseEntity.ok(shoppingService.createPdfFromCustomer(customerId, language))
    }
}