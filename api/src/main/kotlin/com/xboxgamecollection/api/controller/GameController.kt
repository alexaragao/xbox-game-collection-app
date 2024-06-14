package com.xboxgamecollection.api.controller

import com.xboxgamecollection.api.model.Game
import com.xboxgamecollection.api.service.GameService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/games")
class GameController(val gameService: GameService) {

    @GetMapping
    fun listAllGames(
        @RequestParam(required = false) title: String?,
        @RequestParam(required = false) genre: String?
    ): List<Game> {
        return gameService.findAll(title, genre)
    }

}
