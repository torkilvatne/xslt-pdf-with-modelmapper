package com.torkilvatne.xsltpdfwithmodelmapper.services

import org.springframework.stereotype.Service
import com.torkilvatne.xsltpdfwithmodelmapper.exceptions.CustomerNotFoundException
import com.torkilvatne.xsltpdfwithmodelmapper.models.CustomerPDF

@Service
class ShoppingService(
    private val shoppingApiService: ShoppingApiService,
    private val pdfService: PdfService,
    private val qrCodeService: QrCodeService,
) {
    fun createXmlFromCustomer(cid: Int, language: String): String {
        val customer = shoppingApiService.handleGetCustomerById(cid, language)
        if (customer === null) {
            throw CustomerNotFoundException("Customer not found!");
        }
        return pdfService.serializeAsXML(
            CustomerPDF(
                listOf(customer), qrCodeService.createQrCode(
                    "www.vg.no",
                    50,
                    50,
                    "png"
                )
            )
        )
    }

    fun createXmlFromAllCustomers(): String {
        val customers = shoppingApiService.handleGetAllCustomers()
        if (customers.isEmpty()) {
            throw CustomerNotFoundException("No customers in database!");
        }
        return pdfService.serializeAsXML(
            CustomerPDF(
                customers, qrCodeService.createQrCode(
                    "www.vg.no",
                    50,
                    50,
                    "png"
                )
            )
        )
    }

    fun createPdfFromCustomer(cid: Int, language: String): ByteArray {
        return pdfService.generatePdf(createXmlFromCustomer(cid, language))
    }

    fun createPdfFromCustomers(): ByteArray {
        return pdfService.generatePdf(createXmlFromAllCustomers())
    }
}