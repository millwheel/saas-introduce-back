package com.parsimony.saas.controller

import com.parsimony.saas.dto.product.ProductRequest
import com.parsimony.saas.dto.product.ProductResponse
import com.parsimony.saas.entity.ReactionType
import com.parsimony.saas.service.ProductReactionService
import com.parsimony.saas.service.ProductService
import com.parsimony.saas.util.getClientIpAddress
import com.parsimony.saas.util.getUserAgent
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/products")
class ProductController (
    private val productService: ProductService,
    private val productReactionService: ProductReactionService
) {

    @GetMapping("/{slug}")
    @ResponseStatus(HttpStatus.OK)
    fun getProduct(@PathVariable slug: String,
                   @RequestAttribute userId: String,
                   httpServletRequest: HttpServletRequest): ProductResponse {
        val ipAddress = getClientIpAddress(httpServletRequest)
        val userAgent = getUserAgent(httpServletRequest)
        val product = productService.getProduct(slug, userId, ipAddress, userAgent)
        return  ProductResponse(product)
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

    @PostMapping("/{slug}/like")
    @ResponseStatus(HttpStatus.CREATED)
    fun createLikeReaction(
        @PathVariable slug: String,
        @RequestAttribute userId: String
    ) {
        productReactionService.createReaction(slug, userId, ReactionType.LIKE)
    }

    @PostMapping("/{slug}/dislike")
    @ResponseStatus(HttpStatus.CREATED)
    fun createHateReaction(
        @PathVariable slug: String,
        @RequestAttribute userId: String
    ) {
        productReactionService.createReaction(slug, userId, ReactionType.DISLIKE)
    }

    @DeleteMapping("/{slug}/reactions")
    @ResponseStatus(HttpStatus.CREATED)
    fun deleteReaction(
        @PathVariable slug: String,
        @RequestAttribute userId: String
    ) {
        productReactionService.deleteReaction(slug, userId)
    }

}