package com.parsimony.saas.repository

import com.parsimony.saas.entity.Product
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface ProductRepository : JpaRepository<Product, Long> {

    fun findBySlug(slug: String): Optional<Product>
}