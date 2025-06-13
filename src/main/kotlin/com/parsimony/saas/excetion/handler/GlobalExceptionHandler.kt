package com.parsimony.saas.excetion.handler

import com.parsimony.saas.excetion.custom.ConflictException
import com.parsimony.saas.excetion.custom.DataNotFoundException
import com.parsimony.saas.excetion.custom.InvalidInputException
import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

private val logger = KotlinLogging.logger {}

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(InvalidInputException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleInvalidInputException(ex: InvalidInputException, request: HttpServletRequest): ErrorResult {
        val errorResult = ErrorResult(HttpStatus.BAD_REQUEST.value(), ex.message?: "Bad Request", request.requestURI)
        logger.error { "message: ${ex.message}, position: ${ex.stackTrace.firstOrNull()?.toString()}" }
        return errorResult;
    }

    @ExceptionHandler(DataNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleDataNotFoundException(ex: DataNotFoundException, request: HttpServletRequest): ErrorResult {
        val errorResult = ErrorResult(HttpStatus.NOT_FOUND.value(), ex.message?: "Not Found", request.requestURI)
        logger.error { "message: ${ex.message}, position: ${ex.stackTrace.firstOrNull()?.toString()}" }
        return errorResult;
    }

    @ExceptionHandler(ConflictException::class)
    @ResponseStatus(HttpStatus.CONFLICT)
    fun handleConflictException(ex: ConflictException, request: HttpServletRequest): ErrorResult {
        val errorResult = ErrorResult(HttpStatus.CONFLICT.value(), ex.message?: "Conflict", request.requestURI)
        logger.error { "message: ${ex.message}, position: ${ex.stackTrace.firstOrNull()?.toString()}" }
        return errorResult;
    }

}