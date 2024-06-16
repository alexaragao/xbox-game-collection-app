package com.xboxgamecollection.api.repository

import com.xboxgamecollection.api.model.UserGameCollection
import com.xboxgamecollection.api.model.UserGameCollectionKey
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface UserGameCollectionRepository : JpaRepository<UserGameCollection, UserGameCollectionKey> {
    fun findAllByUserId(userId: UUID): List<UserGameCollection>
}
