package com.xboxgamecollection.api.controller

import com.xboxgamecollection.api.model.Game
import com.xboxgamecollection.api.service.GameService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/games")
class GameController(val gameService: GameService) {
    @GetMapping
    fun search(
        @RequestParam search: String?,
        @RequestParam genre: String?
    ): List<Game> {
        return gameService.search(search, genre)
    }

    @GetMapping("/{gameId}")
    fun getById(@PathVariable gameId: String): ResponseEntity<Game> {
        val gameUUID = UUID.fromString(gameId)
        val game = gameService.findById(gameUUID)

        return if (game != null) {
            ResponseEntity.ok(game)
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
        }
    }

}
