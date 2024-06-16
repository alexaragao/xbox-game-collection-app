package com.xboxgamecollection.api.service

import com.xboxgamecollection.api.model.UserGameCollection
import com.xboxgamecollection.api.repository.UserGameCollectionRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserGameCollectionService(
    private val userGameCollectionRepository: UserGameCollectionRepository
) {

    fun findAllByUserId(userId: UUID): List<UserGameCollection> {
        val userGames = userGameCollectionRepository.findAllByUserId(userId)

        return mutableListOf()
    }

}
