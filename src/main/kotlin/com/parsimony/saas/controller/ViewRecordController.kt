package com.parsimony.saas.controller

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class ViewRecordController {

    @PostMapping("/products/{productId}/views")
    @ResponseStatus(HttpStatus.CREATED)
    fun recordView(
        @PathVariable productId: Long,
        request: HttpServletRequest
    ) {
        // TODO: 조회 기록 남기기 구현 (IP 주소, User Agent 등 기록)
        TODO("구현 필요")
    }

    @GetMapping("/products/{productId}/views/count")
    @ResponseStatus(HttpStatus.OK)
    fun getViewCount(
        @PathVariable productId: Long,
        @RequestParam(defaultValue = "30d") period: String
    ) {
        // TODO: 최근 30일 조회수 카운트 구현
        TODO("구현 필요")
    }
}