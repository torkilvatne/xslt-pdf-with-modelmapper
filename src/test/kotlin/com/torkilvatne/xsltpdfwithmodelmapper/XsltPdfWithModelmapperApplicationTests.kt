package com.torkilvatne.xsltpdfwithmodelmapper

import com.torkilvatne.xsltpdfwithmodelmapper.services.PdfService
import mu.KotlinLogging
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.rendering.ImageType
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.util.StreamUtils
import java.io.*
import org.apache.pdfbox.tools.imageio.ImageIOUtil
import org.apache.pdfbox.rendering.PDFRenderer

private val logger = KotlinLogging.logger {}

@SpringBootTest
class XsltPdfWithModelmapperApplicationTests(
    @Autowired
    private val pdfService: PdfService,
) {

    @Test
    fun contextLoads() {
    }

    @Test
    fun generateTestPdfs() {
        generateTestPdfsWrapper(XML_ALL_CUSTOMERS, "allCustomers")
        generateTestPdfsWrapper(XML_SINGLE_CUSTOMER, "singleCustomer")
    }

    fun generateTestPdfsWrapper(
        xml: String,
        fileName: String,
    ) {
        logger.info { "Starting test 'generateTestPdfsWrapper'." }
        val pdfInBytes = pdfService.convertToPDF(xml)
        logger.info { "Creating pdf for test doc." }
        val file = File("src/test/kotlin/com/torkilvatne/xsltpdfwithmodelmapper/result/${fileName}/${fileName}.pdf")
        StreamUtils.copy(pdfInBytes.inputStream(), file.outputStream())
        try {
            PDDocument.load(file).use { document ->
                val pdfRenderer = PDFRenderer(document)
                for (page in 0 until document.numberOfPages) {
                    val bim = pdfRenderer.renderImageWithDPI(page, 200f, ImageType.RGB)
                    val fileName =
                        "src/test/kotlin/com/torkilvatne/xsltpdfwithmodelmapper/result/${fileName}/image-$page.jpg"
                    ImageIOUtil.writeImage(bim, fileName, 150)
                    logger.info { "Created jpg number ${page} of ${document.numberOfPages}." }
                }
                document.close()
            }
        } catch (e: IOException) {
            logger.warn { "Test failed, check stack trace." }
            e.printStackTrace()
        }
    }

    fun createTestFolder(fileName: String) {
        doesFolderExcist("src/test/kotlin/com/torkilvatne/xsltpdfwithmodelmapper/result/${fileName}/")
    }

    fun doesFileExcist(file: File) {
        if (file.exists()) {
            val fileDeleted: Boolean = file.delete()
            println("File $file did excist. Deleted the old file=$fileDeleted")
        }
    }

    fun doesFolderExcist(
        path: String
    ) {
        if (!File(path).exists()) {
            val folderCreated: Boolean = File(path).mkdir()
            println("The folder path $path did not excist. Was created=$folderCreated")
        }
    }

    val XML_ALL_CUSTOMERS =
        "<CustomerPDF><qrCode><base64>iVBORw0KGgoAAAANSUhEUgAAADIAAAAyAQAAAAA2RLUcAAAAiUlEQVR4XnXQOwpEIQwFUMH2gVsJ2AaydcFWcCuBtA8yamTMCGNzQMjNJ+h6JfyTA+UQj6JZSZwxJ8heuET5UbFbvckh73xTVcj6m6NPw+QsXJ85x/ZtkFGODFKAjioYpDpVgOf/duyVsB/HXG8jZ8Q+c53Ac46vVLQ6FRnWncyxFz8r37zve/kBHAnYtZU0KrQAAAAASUVORK5CYII=</base64><width>50</width><height>50</height><format>png</format><url>url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADIAAAAyAQAAAAA2RLUcAAAAiUlEQVR4XnXQOwpEIQwFUMH2gVsJ2AaydcFWcCuBtA8yamTMCGNzQMjNJ+h6JfyTA+UQj6JZSZwxJ8heuET5UbFbvckh73xTVcj6m6NPw+QsXJ85x/ZtkFGODFKAjioYpDpVgOf/duyVsB/HXG8jZ8Q+c53Ac46vVLQ6FRnWncyxFz8r37zve/kBHAnYtZU0KrQAAAAASUVORK5CYII=)</url></qrCode><customers><customer><customerId>123</customerId><name><firstName>Torkil</firstName><lastName>Vatne</lastName></name><address><street>Fredriksborgveien</street><city>Oslo</city></address><orders><order><customerId>123</customerId><product><productId>111</productId><price>100</price></product></order><order><customerId>123</customerId><product><productId>222</productId><price>299</price></product></order></orders></customer><customer><customerId>456</customerId><name><firstName>Eric</firstName><lastName>Berhan</lastName></name><address><street>Espa Boller</street><city>Espa</city></address><orders><order><customerId>456</customerId><product><productId>222</productId><price>299</price></product></order><order><customerId>456</customerId><product><productId>333</productId><price>59</price></product></order><order><customerId>456</customerId><product><productId>333</productId><price>59</price></product></order><order><customerId>456</customerId><product><productId>444</productId><price>1999</price></product></order></orders></customer><customer><customerId>789</customerId><name><firstName>Ola</firstName><lastName>Normann</lastName></name><address><street>Jotunheimveien</street><city>Jotun</city></address><orders><order><customerId>789</customerId><product><productId>444</productId><price>1999</price></product></order><order><customerId>789</customerId><product><productId>111</productId><price>100</price></product></order><order><customerId>789</customerId><product><productId>222</productId><price>299</price></product></order></orders></customer></customers></CustomerPDF>"
    val XML_SINGLE_CUSTOMER =
        "<CustomerPDF><qrCode><base64>iVBORw0KGgoAAAANSUhEUgAAADIAAAAyAQAAAAA2RLUcAAAAiUlEQVR4XnXQOwpEIQwFUMH2gVsJ2AaydcFWcCuBtA8yamTMCGNzQMjNJ+h6JfyTA+UQj6JZSZwxJ8heuET5UbFbvckh73xTVcj6m6NPw+QsXJ85x/ZtkFGODFKAjioYpDpVgOf/duyVsB/HXG8jZ8Q+c53Ac46vVLQ6FRnWncyxFz8r37zve/kBHAnYtZU0KrQAAAAASUVORK5CYII=</base64><width>50</width><height>50</height><format>png</format><url>url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADIAAAAyAQAAAAA2RLUcAAAAiUlEQVR4XnXQOwpEIQwFUMH2gVsJ2AaydcFWcCuBtA8yamTMCGNzQMjNJ+h6JfyTA+UQj6JZSZwxJ8heuET5UbFbvckh73xTVcj6m6NPw+QsXJ85x/ZtkFGODFKAjioYpDpVgOf/duyVsB/HXG8jZ8Q+c53Ac46vVLQ6FRnWncyxFz8r37zve/kBHAnYtZU0KrQAAAAASUVORK5CYII=)</url></qrCode><customers><customer><customerId>123</customerId><name><firstName>Torkil</firstName><lastName>Vatne</lastName></name><address><street>Fredriksborgveien</street><city>Oslo</city></address><orders><order><customerId>123</customerId><product><productId>111</productId><price>100</price></product></order><order><customerId>123</customerId><product><productId>222</productId><price>299</price></product></order></orders></customer></customers></CustomerPDF>"


}
