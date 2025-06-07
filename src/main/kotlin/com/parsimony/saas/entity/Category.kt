package com.parsimony.saas.entity

import jakarta.persistence.*

@Entity
class Category(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val slug: String,

    val name: String,

)
