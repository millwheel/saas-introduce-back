package com.parsimony.saas.repository

import com.parsimony.saas.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, String> {
}