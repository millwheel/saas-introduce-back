package com.parsimony.saas.service

import com.parsimony.saas.dto.topic.TopicRequest
import com.parsimony.saas.dto.topic.TopicResponse
import com.parsimony.saas.entity.Topic
import com.parsimony.saas.entity.TopicQueryModel
import com.parsimony.saas.excetion.custom.ConflictException
import com.parsimony.saas.excetion.custom.InvalidInputException
import com.parsimony.saas.repository.ProductRepository
import com.parsimony.saas.repository.TopicRepository
import com.parsimony.saas.util.orThrowNotFound
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class TopicService (
    private val topicRepository: TopicRepository,
){

    fun getTopics() : List<TopicQueryModel> {
        return topicRepository.findAllQueryModel();
    }

    fun getTopic(slug: String): TopicResponse {
        val topic = topicRepository.findBySlug(slug)
            .orThrowNotFound("Category", "slug", slug)
        return TopicResponse(topic, topic.products)
    }

    @Transactional
    fun createTopic(topicRequest: TopicRequest) {
        validateRequest(topicRequest)
        val topic = Topic(topicRequest)
        topicRepository.save(topic)
    }

    @Transactional
    fun updateTopic(slug: String, topicRequest: TopicRequest) {
        val topic = topicRepository.findBySlug(slug)
            .orThrowNotFound("topic", "slug", slug)
        validateRequest(topicRequest)
        topic.update(topicRequest)
    }

    private fun validateRequest(topicRequest: TopicRequest) {
        if (!topicRequest.slug.startsWith("/")) {
            throw InvalidInputException("slug should start with slash(/)")
        }
        if (topicRepository.existsBySlug(topicRequest.slug)) {
            throw ConflictException("topic slug already used")
        }
        if (topicRepository.existsByName(topicRequest.name)) {
            throw ConflictException("topic name already used")
        }
    }

}