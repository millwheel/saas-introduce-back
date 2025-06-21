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

    fun getProduct(id: Long): Product {
        return productRepository.findById(id)
            .orThrowNotFound("product", "id", id)
    }

    @Transactional
    fun createProduct(productRequest: ProductRequest) {
        val product = Product(productRequest)
        productRepository.save(product)
        val statistic = ProductViewStatistic(product = product)
        productViewStatisticRepository.save(statistic)
    }


    @Transactional
    fun updateProduct(id: Long, productRequest: ProductRequest) {
        val product = productRepository.findById(id)
            .orThrowNotFound("product", "id", id)
        product.update(productRequest)
    }

    @Transactional
    fun deleteProduct(id: Long) {
        val product = productRepository.findById(id)
            .orThrowNotFound("product", "id", id)
        productRepository.delete(product)
    }

}