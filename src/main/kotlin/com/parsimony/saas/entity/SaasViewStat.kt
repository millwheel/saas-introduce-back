package com.parsimony.saas.entity

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
class SaasViewStat(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "saas_id")
    val saas: Saas,

    val userId: Long?,

    val ipAddress: String,

    val userAgent: String,

    val viewedAt: LocalDateTime = LocalDateTime.now()
)