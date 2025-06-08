package com.parsimony.saas.util

import jakarta.servlet.http.HttpServletRequest
import kotlin.text.split

fun getClientIpAddress(request: HttpServletRequest): String {
    val xForwardedFor = request.getHeader("X-Forwarded-For")
    return when {
        !xForwardedFor.isNullOrBlank() -> xForwardedFor.split(",")[0].trim()
        else -> request.remoteAddr
    }
}

fun getUserAgent(request: HttpServletRequest): String {
    return request.getHeader("User-Agent") ?: ""
}