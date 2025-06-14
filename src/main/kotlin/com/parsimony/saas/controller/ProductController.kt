package com.parsimony.saas.controller

import com.parsimony.saas.dto.product.ProductRequest
import com.parsimony.saas.dto.product.ProductResponse
import com.parsimony.saas.entity.ReactionType
import com.parsimony.saas.service.ProductReactionService
import com.parsimony.saas.service.ProductService
import com.parsimony.saas.service.ProductViewService
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
    private val productReactionService: ProductReactionService,
    private val productViewService: ProductViewService
) {

    @GetMapping("/{code}")
    @ResponseStatus(HttpStatus.OK)
    fun getProduct(@PathVariable code: String,
                   @RequestAttribute userId: String,
                   httpServletRequest: HttpServletRequest): ProductResponse {
        val ipAddress = getClientIpAddress(httpServletRequest)
        val userAgent = getUserAgent(httpServletRequest)
        val product = productService.getProduct(code)
        productViewService.saveView(product, userId, ipAddress, userAgent)
        val productViewStatistic = productViewService.getProductViewStatistic(product)
        return ProductResponse(product, productViewStatistic)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createProduct(@RequestBody productRequest: ProductRequest) {
        productService.createProduct(productRequest)
    }

    @PutMapping("/{code}")
    @ResponseStatus(HttpStatus.OK)
    fun updateProduct(
        @PathVariable code: String,
        @RequestBody productRequest: ProductRequest
    ){
        productService.updateProduct(code, productRequest)
    }

    @DeleteMapping("/{code}")
    @ResponseStatus(HttpStatus.OK)
    fun deleteProduct(@PathVariable code: String) {
        productService.deleteProduct(code)
    }

    @PostMapping("/{code}/like")
    @ResponseStatus(HttpStatus.CREATED)
    fun createLikeReaction(
        @PathVariable code: String,
        @RequestAttribute userId: String
    ) {
        productReactionService.createReaction(code, userId, ReactionType.LIKE)
    }

    @PostMapping("/{code}/dislike")
    @ResponseStatus(HttpStatus.CREATED)
    fun createDislikeReaction(
        @PathVariable code: String,
        @RequestAttribute userId: String
    ) {
        productReactionService.createReaction(code, userId, ReactionType.DISLIKE)
    }

    @DeleteMapping("/{code}/reactions")
    @ResponseStatus(HttpStatus.CREATED)
    fun deleteReaction(
        @PathVariable code: String,
        @RequestAttribute userId: String
    ) {
        productReactionService.deleteReaction(code, userId)
    }

}