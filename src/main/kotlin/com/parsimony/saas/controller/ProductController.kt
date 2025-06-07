package com.parsimony.saas.controller

import jakarta.validation.constraints.Min
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/products")
class ProductController {

    @GetMapping
    fun getProducts(
        @RequestParam(defaultValue = "0") @Min(0) page: Int,
        @RequestParam(defaultValue = "10") @Min(1) size: Int,
        @RequestParam(required = false) categoryId: Long?
    ) {
        // TODO: product 목록 조회 기능 구현
    }

    @GetMapping("/{productId}")
    fun getProduct(@PathVariable productId: Long) {
        // TODO: 특정 Product 상세 조회 구현
    }

    @PostMapping
    fun createProduct() {
        // TODO: Product 등록 구현 (권한 체크: 관리자/에디터용)
    }

    @PutMapping("/{productId}")
    fun updateProduct(
        @PathVariable productId: Long
    ){
        // TODO: Product 정보 수정 구현
    }

    @DeleteMapping("/{productId}")
    fun deleteProduct(@PathVariable productId: Long) {
        // TODO: Product 삭제 구현
    }

    @GetMapping("/popular")
    fun getPopularProducts(@RequestParam(defaultValue = "30d") period: String) {
        // TODO: 최근 30일 좋아요 + 조회수 기준 인기순 구현
    }

    @GetMapping("/recent")
    fun getRecentProducts() {
        // TODO: 최신 등록된 Product 목록 구현
    }

}