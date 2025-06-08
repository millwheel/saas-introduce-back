package com.parsimony.saas.repository

import com.parsimony.saas.entity.Product
import com.parsimony.saas.entity.ProductViewStatistic
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.Optional

interface ProductViewStatisticRepository : JpaRepository<ProductViewStatistic, Long> {

    fun findByProduct(product: Product): Optional<ProductViewStatistic>

    // 최근 7일간 인기 상품 TOP 3
    @Query("""
        SELECT p FROM ProductViewStatistic p 
        ORDER BY p.last7DaysViews DESC 
        LIMIT 3
    """)
    fun findTop3ByLast7Days(): List<ProductViewStatistic>

    // 최근 30일간 인기 상품 TOP 3
    @Query("""
        SELECT p FROM ProductViewStatistic p 
        ORDER BY p.last30DaysViews DESC 
        LIMIT 3
    """)
    fun findTop3ByLast30Days(): List<ProductViewStatistic>

}