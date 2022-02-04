package com.torkilvatne.xsltpdfwithmodelmapper.services

import org.springframework.stereotype.Service
import com.torkilvatne.xsltpdfwithmodelmapper.exceptions.CustomerNotFoundException
import com.torkilvatne.xsltpdfwithmodelmapper.models.CustomerPDF

@Service
class ShoppingService(
    private val shoppingApiService: ShoppingApiService,
    private val pdfService: PdfService,
) {
    fun createXmlFromCustomer(cid: Int, language: String): String {
        val customer = shoppingApiService.handleGetCustomerById(cid, language)
        if (customer === null) {
            throw CustomerNotFoundException("Customer not found!");
        }
        return pdfService.serializeAsXML(CustomerPDF(listOf(customer)))
    }

    fun createPdfFromCustomer(cid: Int, language: String): ByteArray? {
        return pdfService.generatePdf(createXmlFromCustomer(cid, language))
    }
}