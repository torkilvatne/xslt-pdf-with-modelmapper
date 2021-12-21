package com.torkilvatne.xsltpdfwithmodelmapper.exceptions

class CustomerNotFoundException(msg: String): Exception(msg)

class CannotSerializeCustomerException(msg: String): Exception(msg)