package com.parsimony.saas.repository

import com.parsimony.saas.entity.Saas
import org.springframework.data.jpa.repository.JpaRepository

interface SaasRepository : JpaRepository<Saas, Long> {
}