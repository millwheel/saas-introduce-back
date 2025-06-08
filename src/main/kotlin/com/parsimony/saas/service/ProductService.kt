package com.parsimony.saas.service

import com.parsimony.saas.dto.product.ProductRequest
import com.parsimony.saas.entity.Product
import com.parsimony.saas.entity.ProductViewLog
import com.parsimony.saas.repository.ProductRepository
import com.parsimony.saas.repository.ProductViewLogRepository
import com.parsimony.saas.util.orThrowNotFound
import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ProductService (
    private val productRepository: ProductRepository,
    private val productViewLogRepository: ProductViewLogRepository
) {

    fun getProduct(slug: String, userId: String, ipAddress: String, userAgent: String): Product {
        val product = productRepository.findBySlug(slug)
            .orThrowNotFound("product", "slug", slug)
        val viewLog = ProductViewLog(
            product = product,
            userId = userId,
            ipAddress = ipAddress,
            userAgent = userAgent
        )
        productViewLogRepository.save(viewLog)
        return product
    }

    @Transactional
    fun createProduct(productRequest: ProductRequest) {
        val product = Product(productRequest)
        productRepository.save(product)
    }


    @Transactional
    fun updateProduct(slug: String, productRequest: ProductRequest) {
        productRepository.findBySlug(slug)
            .orThrowNotFound("product", "slug", slug)
    }

    @Transactional
    fun deleteProduct(slug: String) {
        val product = productRepository.findBySlug(slug)
            .orThrowNotFound("product", "slug", slug)
        productRepository.delete(product)
    }

}