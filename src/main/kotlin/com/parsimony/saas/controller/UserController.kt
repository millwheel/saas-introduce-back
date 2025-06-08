package com.parsimony.saas.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(
) {

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    fun getMyProfile() {
        // TODO: 내 프로필 정보 조회 구현 (인증된 사용자)
        TODO("구현 필요")
    }


    @GetMapping("/me/reactions")
    @ResponseStatus(HttpStatus.OK)
    fun getMyReactions() {
        // TODO: 내가 반응한 Product 목록 조회 구현 (필터: LIKE/DISLIKE)
        TODO("구현 필요")
    }
}