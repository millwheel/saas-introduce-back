package com.parsimony.saas.service

import com.parsimony.saas.dto.product.ProductRequest
import com.parsimony.saas.entity.Product
import com.parsimony.saas.entity.ProductViewLog
import com.parsimony.saas.repository.ProductRepository
import com.parsimony.saas.repository.ProductViewLogRepository
import com.parsimony.saas.util.orThrowNotFound
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

private val logger = KotlinLogging.logger {}

@Service
@Transactional(readOnly = true)
class ProductService (
    private val productRepository: ProductRepository,
    private val productViewLogRepository: ProductViewLogRepository
) {

    fun getProduct(slug: String): Product {
        return productRepository.findBySlug(slug)
            .orThrowNotFound("product", "slug", slug)
    }

    @Async
    @Transactional
    fun saveView(product: Product, userId: String?, ipAddress: String, userAgent: String) {
        try {
            val viewLog = ProductViewLog(
                product = product,
                userId = userId,
                ipAddress = ipAddress,
                userAgent = userAgent
            )
            productViewLogRepository.save(viewLog)
        } catch (e: Exception) {
            logger.error { "exception: ${e.message}" }
        }
    }

    @Transactional
    fun createProduct(productRequest: ProductRequest) {
        val product = Product(productRequest)
        productRepository.save(product)
    }


    @Transactional
    fun updateProduct(slug: String, productRequest: ProductRequest) {
        val product = productRepository.findBySlug(slug)
            .orThrowNotFound("product", "slug", slug)
        product.update(productRequest)
    }

    @Transactional
    fun deleteProduct(slug: String) {
        val product = productRepository.findBySlug(slug)
            .orThrowNotFound("product", "slug", slug)
        productRepository.delete(product)
    }

}