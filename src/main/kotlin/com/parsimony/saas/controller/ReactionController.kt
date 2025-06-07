package com.parsimony.saas.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class ReactionController {

    @PostMapping("/products/{productId}/reactions")
    @ResponseStatus(HttpStatus.CREATED)
    fun createOrUpdateReaction(
        @PathVariable productId: Long,
    ) {
        // TODO: 좋아요/싫어요 생성 또는 수정 구현
        TODO("구현 필요")
    }

    @GetMapping("/products/{productId}/reactions/count")
    @ResponseStatus(HttpStatus.OK)
    fun getReactionCount(@PathVariable productId: Long) {
        // TODO: 좋아요/싫어요 개수 조회 구현
        TODO("구현 필요")
    }

    @GetMapping("/users/me/reactions")
    @ResponseStatus(HttpStatus.OK)
    fun getMyReactions() {
        // TODO: 내가 반응한 Product 목록 조회 구현 (필터: LIKE/DISLIKE)
        TODO("구현 필요")
    }
}