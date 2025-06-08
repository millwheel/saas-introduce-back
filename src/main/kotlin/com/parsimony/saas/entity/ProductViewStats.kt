package com.parsimony.saas.entity

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "product_view_stats")
class ProductViewStats(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    val product: Product,

    var totalViews: Long = 0,
    var last7DaysViews: Long = 0,
    var last30DaysViews: Long = 0,

    var updatedAt: LocalDateTime = LocalDateTime.now()

) {
    fun incrementTotalViews() {
        totalViews++
        updatedAt = LocalDateTime.now()
    }

    fun updatePeriodViews(last7Days: Long, last30Days: Long) {
        last7DaysViews = last7Days
        last30DaysViews = last30Days
        updatedAt = LocalDateTime.now()
    }
}