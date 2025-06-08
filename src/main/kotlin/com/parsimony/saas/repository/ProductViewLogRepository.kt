package com.parsimony.saas.repository

import com.parsimony.saas.entity.Product
import com.parsimony.saas.entity.ProductViewLog
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface ProductViewLogRepository : JpaRepository<ProductViewLog, Long> {

    fun countByProductAndViewedAtAfter(product: Product, startDateTime: LocalDateTime): Int

}