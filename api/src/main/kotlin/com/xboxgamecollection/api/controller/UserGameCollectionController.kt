package com.xboxgamecollection.api.controller

import com.xboxgamecollection.api.model.Game
import com.xboxgamecollection.api.service.UserGameCollectionService
import com.xboxgamecollection.api.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user/game/collection")
class UserGameCollectionController(
    private val userService: UserService,
    private val userGameCollectionService: UserGameCollectionService
) {

    @GetMapping
    fun getUserGameCollection(@AuthenticationPrincipal loggedUser: User): ResponseEntity<List<Game>> {
        val user = userService.findByNickname(loggedUser.username)!!

        val userGameCollection = userGameCollectionService.findAllByUserId(user.id)
        val userGames = userGameCollection.map { it.game }

        return ResponseEntity.status(HttpStatus.OK).body(userGames)
    }

}
