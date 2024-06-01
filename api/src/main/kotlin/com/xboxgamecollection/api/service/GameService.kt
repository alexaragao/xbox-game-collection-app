package com.xboxgamecollection.api.service

import com.xboxgamecollection.api.model.Game
import com.xboxgamecollection.api.repository.GameRepository
import org.springframework.stereotype.Service

@Service
class GameService(private val gameRepository: GameRepository) {

    fun findAll(): List<Game> = gameRepository.findAll()

    fun findById(id: Long): Game? = gameRepository.findById(id).orElse(null)

    fun save(game: Game): Game = gameRepository.save(game)

    fun deleteById(id: Long) = gameRepository.deleteById(id)

}
