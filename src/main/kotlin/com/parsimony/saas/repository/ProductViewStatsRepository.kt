package com.parsimony.saas.repository

import com.parsimony.saas.entity.ProductViewStats
import org.springframework.data.jpa.repository.JpaRepository

interface ProductViewStatsRepository : JpaRepository<ProductViewStats, Long> {
}