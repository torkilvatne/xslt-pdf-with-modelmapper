package com.torkilvatne.xsltpdfwithmodelmapper.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import com.torkilvatne.xsltpdfwithmodelmapper.exceptions.CustomerNotFoundException

@ControllerAdvice
class ShoppingExceptionController {
    @ExceptionHandler(value = [CustomerNotFoundException::class])
    fun handleUserAlreadyExists(): ResponseEntity<String> {
        return ResponseEntity("Customer not found!", HttpStatus.BAD_REQUEST)
    }
}