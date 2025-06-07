package com.parsimony.saas.repository

import com.parsimony.saas.entity.ProductViewRecord
import org.springframework.data.jpa.repository.JpaRepository

interface ProductViewRecordRepository : JpaRepository<ProductViewRecord, Long> {
}