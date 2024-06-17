package com.xboxgamecollection.api.service

import com.xboxgamecollection.api.model.Game
import com.xboxgamecollection.api.repository.GameRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class GameService(
    private val gameRepository: GameRepository
) {
    fun search(search: String?, genre: String?): List<Game> = gameRepository.search(search ?: "", genre ?: "")
    
    fun findAll(): List<Game> = gameRepository.findAll()

    fun findById(id: UUID): Game? = gameRepository.findById(id).orElse(null)

    fun save(game: Game): Game = gameRepository.save(game)

    fun deleteById(id: UUID) = gameRepository.deleteById(id)

}
