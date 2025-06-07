package com.parsimony.saas.controller

import com.parsimony.saas.dto.category.TopicRequest
import com.parsimony.saas.dto.category.TopicResponse
import com.parsimony.saas.service.TopicService
import org.springframework.http.HttpStatus
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
    fun getTopics() : List<TopicResponse> {
        val topics = topicService.getTopics()
        return topics.map { TopicResponse(it) }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createTopic(@RequestBody request: TopicRequest) {
        topicService.createTopic(request)
    }

    @PutMapping("/{slug}")
    @ResponseStatus(HttpStatus.OK)
    fun updateTopic(
        @PathVariable slug: String,
        @RequestBody request: TopicRequest
    ) {
        topicService.updateTopic(slug, request)
    }

}