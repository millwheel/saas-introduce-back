package com.parsimony.saas.repository

import com.parsimony.saas.entity.Product
import com.parsimony.saas.entity.ProductViewStats
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.Optional

interface ProductViewStatsRepository : JpaRepository<ProductViewStats, Long> {

    fun findByProduct(product: Product): Optional<ProductViewStats>

    // 최근 7일간 인기 상품 TOP 3
    @Query("""
        SELECT p FROM ProductViewStats p 
        ORDER BY p.last7DaysViews DESC 
        LIMIT 3
    """)
    fun findTop3ByLast7Days(): List<ProductViewStats>

    // 최근 30일간 인기 상품 TOP 3
    @Query("""
        SELECT p FROM ProductViewStats p 
        ORDER BY p.last30DaysViews DESC 
        LIMIT 3
    """)
    fun findTop3ByLast30Days(): List<ProductViewStats>

}