package io.onsite.portbase.parcel.config

import io.onsite.portbase.parcel.ParcelNotFoundException
import io.onsite.portbase.parcel.ReceipientNotFound
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ControllerAdviceExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(value = [ReceipientNotFound::class])
    fun handeReceipeintNotFound(ex: Exception, request: WebRequest): ResponseEntity<ErrorsDetails> {
        return ResponseEntity(ErrorsDetails("The recipient was not found"), HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(value = [ParcelNotFoundException::class])
    fun handleParcelNotFound(ex: Exception, request: WebRequest): ResponseEntity<ErrorsDetails> {
        return ResponseEntity(ErrorsDetails("The parcel was not found"), HttpStatus.NOT_FOUND)
    }
}

data class ErrorsDetails(
    val details: String
)