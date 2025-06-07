package com.parsimony.saas.repository

import com.parsimony.saas.entity.Product
import com.parsimony.saas.entity.ProductReaction
import com.parsimony.saas.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface ProductReactionRepository : JpaRepository<ProductReaction, Long> {

    fun existsByProductAndUser(product: Product, user: User): Boolean

    fun deleteByProductAndUser(product: Product, user: User)

}