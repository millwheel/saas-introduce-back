package com.parsimony.saas.repository

import com.parsimony.saas.entity.SaasViewRecord
import org.springframework.data.jpa.repository.JpaRepository

interface SaasViewRecordRepository : JpaRepository<SaasViewRecord, Long> {
}