package com.parsimony.saas.repository

import com.parsimony.saas.entity.Product
import org.springframework.data.jpa.repository.JpaRepository

interface SaasRepository : JpaRepository<Product, Long> {
}