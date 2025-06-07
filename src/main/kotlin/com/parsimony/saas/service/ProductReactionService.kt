package com.parsimony.saas.service

import com.parsimony.saas.entity.ProductReaction
import com.parsimony.saas.entity.ReactionType
import com.parsimony.saas.excetion.ConflictException
import com.parsimony.saas.repository.ProductReactionRepository
import com.parsimony.saas.repository.ProductRepository
import com.parsimony.saas.repository.UserRepository
import com.parsimony.saas.util.orThrowNotFound
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ProductReactionService (
    private val productRepository: ProductRepository,
    private val productReactionRepository: ProductReactionRepository,
    private val userRepository: UserRepository,
) {

    @Transactional
    fun createReaction(slug: String, userId: String, reactionType: ReactionType) {
        val product = productRepository.findBySlug(slug)
            .orThrowNotFound("product", "slug", slug)
        val user = userRepository.findById(userId)
            .orThrowNotFound("user", "userId", userId)
        if (productReactionRepository.existsByProductAndUser(product, user)) {
            throw ConflictException("You already have a reaction for this product")
        }
        val productReaction = ProductReaction.of(product, user, reactionType)
        productReactionRepository.save(productReaction)
    }

    @Transactional
    fun deleteReaction(slug: String, userId: String) {
        val product = productRepository.findBySlug(slug)
            .orThrowNotFound("product", "slug", slug)
        val user = userRepository.findById(userId)
            .orThrowNotFound("user", "userId", userId)
        productReactionRepository.deleteByProductAndUser(product, user)
    }
}