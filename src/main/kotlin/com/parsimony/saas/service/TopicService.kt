package com.parsimony.saas.service

import com.parsimony.saas.dto.category.TopicRequest
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
        // TODO topic에 연결된 Product 정보를 돌려줘야함
    }

    @Transactional
    fun createTopic(topicRequest: TopicRequest) {
        validateRequest(topicRequest)
        val topic = Topic(topicRequest)
        topicRepository.save(topic)
    }

    @Transactional
    fun updateTopic(slug: String, topicRequest: TopicRequest) {
        val category = topicRepository.findBySlug(slug)
            .orThrowNotFound("Category", "slug", slug)
        validateRequest(topicRequest)
        category.update(topicRequest)
    }

    private fun validateRequest(topicRequest: TopicRequest) {
        if (topicRepository.existsBySlug(topicRequest.slug)) {
            throw ConflictException("category slug already used")
        }
        if (topicRepository.existsByName(topicRequest.name)) {
            throw ConflictException("category name already used")
        }
    }

}