package com.parsimony.saas.controller

import com.parsimony.saas.dto.product.ProductRequest
import com.parsimony.saas.service.ProductService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/products")
class ProductController (
    private val productService: ProductService
) {

    @GetMapping("/{slug}")
    @ResponseStatus(HttpStatus.OK)
    fun getProduct(@PathVariable slug: String) {
        // TODO: 특정 Product 상세 조회 구현
        // TODO: 최근 30일 조회수까지 읽어오기 구현
        // TODO: 읽고 나서 비동기적으로 조회수 1 증가시키도록 구현하기
        TODO("구현 필요")
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createProduct(@RequestBody productRequest: ProductRequest) {
        productService.createProduct(productRequest)
    }

    @PutMapping("/{slug}")
    @ResponseStatus(HttpStatus.OK)
    fun updateProduct(
        @PathVariable slug: String,
        @RequestBody productRequest: ProductRequest
    ){
        productService.updateProduct(slug, productRequest)
    }

    @DeleteMapping("/{slug}")
    @ResponseStatus(HttpStatus.OK)
    fun deleteProduct(@PathVariable slug: String,) {
        productService.deleteProduct(slug)
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