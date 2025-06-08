package com.parsimony.saas.service

import com.parsimony.saas.entity.Product
import com.parsimony.saas.entity.ProductViewLog
import com.parsimony.saas.entity.ProductViewStats
import com.parsimony.saas.repository.ProductViewLogRepository
import com.parsimony.saas.repository.ProductViewStatsRepository
import com.parsimony.saas.util.orThrowNotFound
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

private val logger = KotlinLogging.logger {}


@Service
@Transactional(readOnly = true)
class ProductViewService (
    private val productViewLogRepository: ProductViewLogRepository,
    private val productViewStatsRepository: ProductViewStatsRepository
) {

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

            val stats = productViewStatsRepository.findByProduct(product)
                .orThrowNotFound("stats", "productId", product.id)
            stats.incrementTotalViews()

        } catch (e: Exception) {
            logger.error { "exception: ${e.message}" }
        }
    }

}