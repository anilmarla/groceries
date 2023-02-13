package com.anil.groceries.network

data class CustomException(var statusCode: Int? = 0, var errorMessage: String = "", var errorFlag: String? = null) : Exception(errorMessage)