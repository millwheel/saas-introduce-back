package com.parsimony.saas.repository

import com.parsimony.saas.entity.Product
import com.parsimony.saas.entity.Topic
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface ProductRepository : JpaRepository<Product, Long> {

    fun findBySlug(slug: String): Optional<Product>

    fun findByTopicsContaining(topic: Topic): List<Product>

}