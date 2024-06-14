package com.xboxgamecollection.api.controller

import com.xboxgamecollection.api.model.Game
import com.xboxgamecollection.api.service.GameService
import org.springframework.data.jpa.repository.Query
import org.springframework.web.bind.annotation.*

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

}
