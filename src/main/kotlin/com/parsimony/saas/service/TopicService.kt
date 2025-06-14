package com.parsimony.saas.service

import com.parsimony.saas.dto.topic.TopicRequest
import com.parsimony.saas.dto.topic.TopicResponse
import com.parsimony.saas.entity.Topic
import com.parsimony.saas.entity.TopicQueryModel
import com.parsimony.saas.excetion.custom.ConflictException
import com.parsimony.saas.excetion.custom.InvalidInputException
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

    fun getTopic(code: String): Topic {
        return topicRepository.findByCode(code)
            .orThrowNotFound("topic", "code", code)
    }

    @Transactional
    fun createTopic(topicRequest: TopicRequest) {
        validateRequest(topicRequest)
        val topic = Topic(topicRequest)
        topicRepository.save(topic)
    }

    @Transactional
    fun updateTopic(topic: Topic, topicRequest: TopicRequest) {
        validateRequest(topicRequest)
        topic.update(topicRequest)
    }

    private fun validateRequest(topicRequest: TopicRequest) {
        if (topicRequest.code.startsWith("/")) {
            throw InvalidInputException("code should start without slash(/)")
        }
        if (topicRepository.existsByCode(topicRequest.code)) {
            throw ConflictException("topic code already used")
        }
        if (topicRepository.existsByName(topicRequest.name)) {
            throw ConflictException("topic name already used")
        }
    }

    fun deleteTopic(topic: Topic) {
        topicRepository.delete(topic)
    }

}