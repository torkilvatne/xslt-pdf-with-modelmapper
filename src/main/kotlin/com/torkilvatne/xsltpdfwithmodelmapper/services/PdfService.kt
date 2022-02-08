package com.torkilvatne.xsltpdfwithmodelmapper.services

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.torkilvatne.xsltpdfwithmodelmapper.exceptions.CannotSerializeCustomerException
import mu.KotlinLogging
import org.apache.fop.apps.FOPException
import org.apache.fop.apps.Fop
import org.apache.fop.apps.FopFactory
import org.apache.fop.apps.MimeConstants
import org.springframework.stereotype.Service
import java.io.*
import javax.xml.transform.Result
import javax.xml.transform.Transformer
import javax.xml.transform.TransformerException
import javax.xml.transform.TransformerFactory
import javax.xml.transform.sax.SAXResult
import javax.xml.transform.stream.StreamSource

private val logger = KotlinLogging.logger {}

@Service
class PdfService {

    fun serializeAsXML(obj: Any): String {
        val xmlModule = JacksonXmlModule()
        val objectMapper: ObjectMapper = XmlMapper(xmlModule)
        try {
            return objectMapper.writeValueAsString(obj)
        } catch (e: CannotSerializeCustomerException) {
            logger.info { "Can't serialize object: ${obj}" }
            throw CannotSerializeCustomerException("Can't serialize object: $obj")
        }
    }

    fun generatePdf(
        xml: String
    ): ByteArray {
        try {
            return convertToPDF(xml)
        } catch (e: FOPException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: TransformerException) {
            e.printStackTrace()
        }
        return byteArrayOf()
    }

    @Throws(IOException::class, FOPException::class, TransformerException::class)
    fun convertToPDF(
        xml: String
    ): ByteArray {
        // Code copied from https://www.netjstech.com/2015/07/how-to-create-pdf-from-xml-using-apache-fop.html
        // the XSL FO file
        val xsltFile = File("src/main/resources/maler/layout.xsl")
        // the XML file which provides the input
        val xmlSource = StreamSource(StringReader(xml))
        // create an instance of fop factory
        val fopFactory = FopFactory.newInstance(File(".").toURI())
        // a user agent is needed for transformation
        val foUserAgent = fopFactory.newFOUserAgent()
        // Setup output
        val baos = ByteArrayOutputStream()
        baos.use { baos ->
            // Construct fop with desired output format
            val fop: Fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, baos)

            // Setup XSLT
            val factory = TransformerFactory.newInstance()
            val transformer: Transformer = factory.newTransformer(StreamSource(xsltFile))

            // Resulting SAX events (the generated FO) must be piped through to FOP
            val res: Result = SAXResult(fop.defaultHandler)

            // Start XSLT transformation and FOP processing
            // That's where the XML is first transformed to XSL-FO and then
            // PDF is created
            transformer.transform(xmlSource, res)
        }
        return baos.toByteArray()
    }
}