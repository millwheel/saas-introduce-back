package com.parsimony.saas.repository

import com.parsimony.saas.entity.SaasReaction
import org.springframework.data.jpa.repository.JpaRepository

interface SaasReactionRepository : JpaRepository<SaasReaction, Long> {
}