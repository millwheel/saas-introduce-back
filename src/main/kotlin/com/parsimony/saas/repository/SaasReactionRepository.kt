package com.parsimony.saas.repository

import com.parsimony.saas.entity.ProductReaction
import org.springframework.data.jpa.repository.JpaRepository

interface SaasReactionRepository : JpaRepository<ProductReaction, Long> {
}