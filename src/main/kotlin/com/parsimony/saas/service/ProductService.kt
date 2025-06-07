package com.parsimony.saas.service

import com.parsimony.saas.dto.product.ProductRequest
import com.parsimony.saas.entity.Product
import com.parsimony.saas.repository.ProductRepository
import com.parsimony.saas.util.orThrowNotFound
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PathVariable

@Service
@Transactional(readOnly = true)
class ProductService (
    private val productRepository: ProductRepository,
) {

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