package com.parsimony.saas.service

import com.parsimony.saas.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService (
    private val productRepository: ProductRepository,
) {


}