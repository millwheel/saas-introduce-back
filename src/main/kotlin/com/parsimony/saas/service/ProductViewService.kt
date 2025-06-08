package com.parsimony.saas.service

import com.parsimony.saas.entity.Product
import com.parsimony.saas.entity.ProductViewLog
import com.parsimony.saas.entity.ProductViewStatistic
import com.parsimony.saas.repository.ProductRepository
import com.parsimony.saas.repository.ProductViewLogRepository
import com.parsimony.saas.repository.ProductViewStatisticRepository
import com.parsimony.saas.util.errorWithLocation
import com.parsimony.saas.util.orThrowNotFound
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

private val logger = KotlinLogging.logger {}


@Service
@Transactional(readOnly = true)
class ProductViewService (
    private val productRepository: ProductRepository,
    private val productViewLogRepository: ProductViewLogRepository,
    private val productViewStatisticRepository: ProductViewStatisticRepository
) {

    fun getProductViewStatistic(product: Product): ProductViewStatistic {
        return productViewStatisticRepository.findByProduct(product)
            .orThrowNotFound("statistic", "productId", product.id)
    }

    fun getTop3ByLast7Days(): List<Product> {
        return productViewStatisticRepository.findTop3ByLast7Days()
            .map { it.product }
    }

    fun getTop3ByLast30Days(): List<Product> {
        return productViewStatisticRepository.findTop3ByLast30Days()
            .map { it.product }
    }

    @Async
    @Transactional
    fun saveView(product: Product, userId: String?, ipAddress: String, userAgent: String) {
        try {
            val viewLog = ProductViewLog(
                product = product,
                userId = userId,
                ipAddress = ipAddress,
                userAgent = userAgent
            )
            productViewLogRepository.save(viewLog)

            val stats = productViewStatisticRepository.findByProduct(product)
                .orThrowNotFound("stats", "productId", product.id)
            stats.incrementTotalViews()

        } catch (e: Exception) {
            logger.errorWithLocation("Failed to update view", e)
        }
    }

    @Scheduled(cron = "0 0 1 * * *") // 매일 새벽 1시 실행
    @Transactional
    fun updatePeriodViewStats() {
        logger.info { "Starting to update period view stats" }

        val now = LocalDateTime.now()
        val last7DaysBefore = now.minusDays(7)
        val last30DaysBefore = now.minusDays(30)

        try {
            val allProducts = productRepository.findAll()

            allProducts.forEach { product ->
                val last7DaysCount = productViewLogRepository.countByProductAndViewedAtAfter(product, last7DaysBefore)

                val last30DaysCount = productViewLogRepository.countByProductAndViewedAtAfter(product, last30DaysBefore)

                val stats = productViewStatisticRepository.findByProduct(product)
                    .orElse(ProductViewStatistic(product = product))

                stats.updatePeriodViews(last7DaysCount, last30DaysCount)
            }

            logger.info { "Successfully updated period view stats for ${allProducts.size} products" }
        } catch (e: Exception) {
            logger.errorWithLocation("Failed to update period view statistic", e)
        }
    }


}