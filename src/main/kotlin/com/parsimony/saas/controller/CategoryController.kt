package com.parsimony.saas.controller

import com.parsimony.saas.service.CategoryService
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
@RequestMapping("/categories")
class CategoryController (
    private val categoryService: CategoryService
){

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAllCategories() {
        // TODO: 모든 카테고리 조회 구현
        TODO("구현 필요")
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createCategory() {
        // TODO: 카테고리 생성 구현
        TODO("구현 필요")
    }

    @PutMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    fun updateCategory(
        @PathVariable categoryId: Long,
    ) {
        // TODO: 카테고리 수정 구현
        TODO("구현 필요")
    }

    @DeleteMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCategory(@PathVariable categoryId: Long) {
        // TODO: 카테고리 삭제 구현
        TODO("구현 필요")
    }
}