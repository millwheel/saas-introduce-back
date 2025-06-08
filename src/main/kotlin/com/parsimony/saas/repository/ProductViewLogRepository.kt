package com.parsimony.saas.repository

import com.parsimony.saas.entity.ProductViewLog
import org.springframework.data.jpa.repository.JpaRepository

interface ProductViewLogRepository : JpaRepository<ProductViewLog, Long> {
}