package com.xboxgamecollection.api.service

import com.xboxgamecollection.api.model.Game
import com.xboxgamecollection.api.repository.GameRepository
import org.springframework.stereotype.Service

@Service
class GameService(
    private val gameRepository: GameRepository
) {

    fun findAll(
        title: String?,
        genre: String?
    ): List<Game> = gameRepository.findAll().filter { game ->
        (title == null || game.title.contains(title, ignoreCase = true)) &&
        (genre == null || game.genre.contains(genre, ignoreCase = true))
    }

    fun findById(id: Long): Game? = gameRepository.findById(id).orElse(null)

    fun save(game: Game): Game = gameRepository.save(game)

    fun deleteById(id: Long) = gameRepository.deleteById(id)

}
