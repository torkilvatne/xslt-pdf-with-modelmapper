package com.torkilvatne.xsltpdfwithmodelmapper.services

import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import com.torkilvatne.xsltpdfwithmodelmapper.models.QrCode
import org.springframework.stereotype.Service
import java.io.ByteArrayOutputStream
import java.nio.charset.StandardCharsets
import java.util.*

@Service
class QrCodeService {

    fun createQrCode(innhold: String, width: Int, height: Int, format: String): QrCode {
        val qrCodeWriter = QRCodeWriter()
        val hints: Map<EncodeHintType, Any> = mapOf(
            EncodeHintType.ERROR_CORRECTION to ErrorCorrectionLevel.M,
            EncodeHintType.CHARACTER_SET to StandardCharsets.UTF_8.name(),
            EncodeHintType.MARGIN to 0
        )

        val bitMatrix = qrCodeWriter.encode(innhold, BarcodeFormat.QR_CODE, width, height, hints)
        val outputStream = ByteArrayOutputStream()
        MatrixToImageWriter.writeToStream(bitMatrix, format, outputStream)
        return QrCode(
            base64 = String(Base64.getEncoder().encode(outputStream.toByteArray())),
            width = width,
            height = height,
            format = format
        )
    }

}