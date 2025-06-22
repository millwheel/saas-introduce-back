package com.parsimony.saas.service

import com.parsimony.saas.dto.product.ProductRequest
import com.parsimony.saas.entity.Product
import com.parsimony.saas.entity.ProductViewStatistic
import com.parsimony.saas.excetion.custom.InvalidInputException
import com.parsimony.saas.repository.ProductRepository
import com.parsimony.saas.repository.ProductViewStatisticRepository
import com.parsimony.saas.repository.TopicRepository
import com.parsimony.saas.util.orThrowNotFound
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@Transactional(readOnly = true)
class ProductService (
    private val topicRepository: TopicRepository,
    private val productRepository: ProductRepository,
    private val productViewStatisticRepository: ProductViewStatisticRepository
) {

    fun getProduct(id: Long): Product {
        return productRepository.findById(id)
            .orThrowNotFound("product", "id", id)
    }

    @Transactional
    fun createProduct(productRequest: ProductRequest) {
        if (productRequest.topicIds == null || productRequest.topicIds.isEmpty()) {
            throw InvalidInputException("topic ids should not be empty")
        }
        val topics = topicRepository.findByIdIn(productRequest.topicIds)
        val product = Product(productRequest, topics)
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
        val productViewStatistic = productViewStatisticRepository.findByProduct(product)
            .orThrowNotFound("product view statistic")
        productViewStatisticRepository.delete(productViewStatistic)
        productRepository.delete(product)
    }

}