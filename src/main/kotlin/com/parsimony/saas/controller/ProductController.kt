package com.parsimony.saas.controller

import jakarta.validation.constraints.Min
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/products")
class ProductController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getProducts(
        @RequestParam(defaultValue = "0") @Min(0) page: Int,
        @RequestParam(defaultValue = "10") @Min(1) size: Int,
        @RequestParam(required = false) categoryId: Long?
    ) {
        // TODO: product 목록 조회 기능 구현
    }

    @GetMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    fun getProduct(@PathVariable productId: Long) {
        // TODO: 특정 Product 상세 조회 구현
        // TODO: 최근 30일 조회수까지 읽어오기 구현
        // TODO: 읽고 나서 비동기적으로 조회수 1 증가시키도록 구현하기
        TODO("구현 필요")
    }

    @PostMapping
    fun createProduct() {
        // TODO: Product 등록 구현 (권한 체크: 관리자/에디터용)
    }

    @PutMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    fun updateProduct(
        @PathVariable productId: Long
    ){
        // TODO: Product 정보 수정 구현
    }

    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    fun deleteProduct(@PathVariable productId: Long) {
        // TODO: Product 삭제 구현
    }

    @PostMapping("/{productId}/reactions")
    @ResponseStatus(HttpStatus.CREATED)
    fun createReaction(
        @PathVariable productId: Long,
    ) {
        // TODO: 좋아요/싫어요 생성 또는 수정 구현
        TODO("구현 필요")
    }

    @DeleteMapping("/{productId}/reactions")
    @ResponseStatus(HttpStatus.CREATED)
    fun deleteReaction(
        @PathVariable productId: Long,
    ) {
        // TODO: 좋아요/싫어요 생성 또는 수정 구현
        TODO("구현 필요")
    }

}