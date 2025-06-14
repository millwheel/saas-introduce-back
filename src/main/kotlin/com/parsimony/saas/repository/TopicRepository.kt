package com.parsimony.saas.repository

import com.parsimony.saas.entity.Topic
import com.parsimony.saas.entity.TopicQueryModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TopicRepository : JpaRepository<Topic, Long> {

    @Query("""
        SELECT new com.parsimony.saas.entity.TopicQueryModel(
            t.id, t.code, t.name, t.emoji, COUNT(p)
        )
        FROM Topic t
        LEFT JOIN t.products p
        GROUP BY t.id, t.code, t.name, t.emoji
    """)
    fun findAllQueryModel(): List<TopicQueryModel>
    fun existsByCode(code: String): Boolean
    fun existsByName(name: String): Boolean
}