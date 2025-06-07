package com.parsimony.saas.repository

import com.parsimony.saas.entity.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository : JpaRepository<Category, Long> {
}