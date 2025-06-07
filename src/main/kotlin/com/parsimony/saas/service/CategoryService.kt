package com.parsimony.saas.service

import com.parsimony.saas.dto.category.CategoryCreateRequest
import com.parsimony.saas.entity.Category
import com.parsimony.saas.excetion.ConflictException
import com.parsimony.saas.excetion.DataNotFoundException
import com.parsimony.saas.repository.CategoryRepository
import com.parsimony.saas.util.orThrowNotFound
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CategoryService (
    private val categoryRepository: CategoryRepository
){

    fun getCategories() {
        categoryRepository.findAll();
    }

    fun getCategory(slug: String): Category {
        return categoryRepository.findBySlug(slug)
            .orThrowNotFound("Category", "slug", slug)
    }

    @Transactional
    fun createCategory(categoryRequest: CategoryCreateRequest) {
        if (categoryRepository.existsBySlug(categoryRequest.slug)) {
            throw ConflictException("category slug already used")
        }
        if (categoryRepository.existsByName(categoryRequest.name)) {
            throw ConflictException("category name already used")
        }
        val category = Category(categoryRequest)
        categoryRepository.save(category)
    }


}