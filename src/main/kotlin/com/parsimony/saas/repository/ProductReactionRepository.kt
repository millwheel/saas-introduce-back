package com.parsimony.saas.repository

import com.parsimony.saas.entity.ProductReaction
import org.springframework.data.jpa.repository.JpaRepository

interface ProductReactionRepository : JpaRepository<ProductReaction, Long> {
}