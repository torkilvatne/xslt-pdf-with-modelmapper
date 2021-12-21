package com.torkilvatne.xsltpdfwithmodelmapper.services

import java.io.ByteArrayOutputStream
import java.io.File
import java.io.StringReader
import javax.xml.transform.TransformerConfigurationException
import javax.xml.transform.TransformerException
import javax.xml.transform.TransformerFactory
import javax.xml.transform.stream.StreamResult
import javax.xml.transform.stream.StreamSource
import org.springframework.stereotype.Service
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.torkilvatne.xsltpdfwithmodelmapper.exceptions.CannotSerializeCustomerException

@Service
class PdfService {

    fun serializeAsXML(obj: Any): String {
        val xmlModule = JacksonXmlModule()
        val objectMapper: ObjectMapper = XmlMapper(xmlModule)
        try {
            return objectMapper.writeValueAsString(obj)
        } catch (e: CannotSerializeCustomerException) {
            throw CannotSerializeCustomerException("Can't serialize object: ${obj.toString()}")
        }
    }

    fun generatePdf(
        xml: String
    ): String {

        val xslSource = StreamSource(File("src/main/resources/maler/xslmal.xsl"))
        val input = StreamSource(StringReader(xml))
        val tf: TransformerFactory = TransformerFactory.newInstance()

        try {
            val transformer = tf.newTransformer(xslSource)
            val baos = ByteArrayOutputStream()
            val result = StreamResult(baos)
            transformer.transform(input, result)
            return String(baos.toByteArray())
        } catch (e: TransformerConfigurationException) {
            throw TransformerConfigurationException()
        } catch (e: TransformerException) {
            throw TransformerException(e.message)
        }
    }

    private fun getResource(resource: String) =
        this.javaClass.getResource(resource)!!.readText()
}