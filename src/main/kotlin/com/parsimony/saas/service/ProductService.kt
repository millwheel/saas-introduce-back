package com.parsimony.saas.service

import com.parsimony.saas.dto.product.ProductRequest
import com.parsimony.saas.entity.Product
import com.parsimony.saas.entity.ProductViewStatistic
import com.parsimony.saas.repository.ProductRepository
import com.parsimony.saas.repository.ProductViewStatisticRepository
import com.parsimony.saas.util.orThrowNotFound
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@Transactional(readOnly = true)
class ProductService (
    private val productRepository: ProductRepository,
    private val productViewStatisticRepository: ProductViewStatisticRepository
) {

    fun getProduct(slug: String): Product {
        return productRepository.findBySlug(slug)
            .orThrowNotFound("product", "slug", slug)
    }

    @Transactional
    fun createProduct(productRequest: ProductRequest) {
        val product = Product(productRequest)
        productRepository.save(product)
        val statistic = ProductViewStatistic(product = product)
        productViewStatisticRepository.save(statistic)
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