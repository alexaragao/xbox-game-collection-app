package com.xboxgamecollection.api.repository

import com.xboxgamecollection.api.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByNickname(nickname: String): User?
}
