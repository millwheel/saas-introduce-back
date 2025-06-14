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

    fun getProduct(code: String): Product {
        return productRepository.findByCode(code)
            .orThrowNotFound("product", "code", code)
    }

    @Transactional
    fun createProduct(productRequest: ProductRequest) {
        val product = Product(productRequest)
        productRepository.save(product)
        val statistic = ProductViewStatistic(product = product)
        productViewStatisticRepository.save(statistic)
    }


    @Transactional
    fun updateProduct(code: String, productRequest: ProductRequest) {
        val product = productRepository.findByCode(code)
            .orThrowNotFound("product", "code", code)
        product.update(productRequest)
    }

    @Transactional
    fun deleteProduct(code: String) {
        val product = productRepository.findByCode(code)
            .orThrowNotFound("product", "code", code)
        productRepository.delete(product)
    }

}