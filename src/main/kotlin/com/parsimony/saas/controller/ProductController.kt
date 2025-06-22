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
import jakarta.validation.Valid
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

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getProduct(@PathVariable id: Long,
                        @RequestAttribute userId: String?,
                        httpServletRequest: HttpServletRequest): ProductResponse {
        val ipAddress = getClientIpAddress(httpServletRequest)
        val userAgent = getUserAgent(httpServletRequest)
        val product = productService.getProduct(id)
        productViewService.saveView(product, userId, ipAddress, userAgent)
        val productViewStatistic = productViewService.getProductViewStatistic(product)
        return ProductResponse(product, productViewStatistic)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createProduct(@Valid @RequestBody productRequest: ProductRequest) {
        productService.createProduct(productRequest)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateProduct(
        @PathVariable id: Long,
        @RequestBody productRequest: ProductRequest
    ){
        productService.updateProduct(id, productRequest)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun deleteProduct(@PathVariable id: Long) {
        productService.deleteProduct(id)
    }

    @PostMapping("/{id}/like")
    @ResponseStatus(HttpStatus.CREATED)
    fun createLikeReaction(
        @PathVariable id: String,
        @RequestAttribute userId: String
    ) {
        productReactionService.createReaction(id, userId, ReactionType.LIKE)
    }

    @PostMapping("/{id}/dislike")
    @ResponseStatus(HttpStatus.CREATED)
    fun createDislikeReaction(
        @PathVariable id: String,
        @RequestAttribute userId: String
    ) {
        productReactionService.createReaction(id, userId, ReactionType.DISLIKE)
    }

    @DeleteMapping("/{id}/reactions")
    @ResponseStatus(HttpStatus.CREATED)
    fun deleteReaction(
        @PathVariable id: String,
        @RequestAttribute userId: String
    ) {
        productReactionService.deleteReaction(id, userId)
    }

}