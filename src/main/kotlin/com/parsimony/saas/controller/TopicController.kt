package com.parsimony.saas.controller

import com.parsimony.saas.dto.topic.TopicRequest
import com.parsimony.saas.dto.topic.TopicResponse
import com.parsimony.saas.dto.topic.TopicSummaryResponse
import com.parsimony.saas.service.TopicService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/topics")
class TopicController (
    private val topicService: TopicService
){

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getTopics() : List<TopicSummaryResponse> {
        val topics = topicService.getTopics()
        return topics.map { TopicSummaryResponse(it) }
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getTopic(@PathVariable id: Long) : TopicResponse {
        val topic = topicService.getTopic(id)
        return TopicResponse(topic, topic.products)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createTopic(@RequestBody request: TopicRequest) {
        topicService.createTopic(request)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateTopic(
        @PathVariable id: Long,
        @RequestBody request: TopicRequest
    ) {
        val topic = topicService.getTopic(id)
        topicService.updateTopic(topic, request)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteTopic(@PathVariable id: Long) {
        val topic = topicService.getTopic(id)
        topicService.deleteTopic(topic)
    }

}