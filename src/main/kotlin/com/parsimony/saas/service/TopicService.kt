package com.parsimony.saas.service

import com.parsimony.saas.dto.category.CategoryRequest
import com.parsimony.saas.entity.Topic
import com.parsimony.saas.excetion.ConflictException
import com.parsimony.saas.repository.TopicRepository
import com.parsimony.saas.util.orThrowNotFound
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class TopicService (
    private val topicRepository: TopicRepository
){

    fun getTopics() : List<Topic> {
        return topicRepository.findAll();
    }

    fun getTopic(slug: String): Topic {
        return topicRepository.findBySlug(slug)
            .orThrowNotFound("Category", "slug", slug)
    }

    @Transactional
    fun createTopic(categoryRequest: CategoryRequest) {
        validateRequest(categoryRequest)
        val topic = Topic(categoryRequest)
        topicRepository.save(topic)
    }

    @Transactional
    fun updateTopic(slug: String, categoryRequest: CategoryRequest) {
        val category = topicRepository.findBySlug(slug)
            .orThrowNotFound("Category", "slug", slug)
        validateRequest(categoryRequest)
        category.update(categoryRequest)
    }

    private fun validateRequest(categoryRequest: CategoryRequest) {
        if (topicRepository.existsBySlug(categoryRequest.slug)) {
            throw ConflictException("category slug already used")
        }
        if (topicRepository.existsByName(categoryRequest.name)) {
            throw ConflictException("category name already used")
        }
    }

}