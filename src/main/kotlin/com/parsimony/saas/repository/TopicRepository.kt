package com.parsimony.saas.repository

import com.parsimony.saas.entity.Topic
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface TopicRepository : JpaRepository<Topic, Long> {

    fun findBySlug(slug: String): Optional<Topic>
    fun existsBySlug(slug: String): Boolean
    fun existsByName(name: String): Boolean
}