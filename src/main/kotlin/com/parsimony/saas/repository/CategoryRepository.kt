package com.parsimony.saas.repository

import com.parsimony.saas.entity.Category
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface CategoryRepository : JpaRepository<Category, Long> {

    fun findBySlug(slug: String): Optional<Category>
}