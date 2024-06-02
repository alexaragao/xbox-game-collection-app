package com.xboxgamecollection.api.controller

import com.xboxgamecollection.api.dto.AuthenticationLoginRequest
import com.xboxgamecollection.api.dto.AuthenticationRegisterRequest
import com.xboxgamecollection.api.model.User
import com.xboxgamecollection.api.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.*

@RestController
class AuthenticationController(
    private val authenticationManager: AuthenticationManager,
    private val userService: UserService
) {
    @PostMapping("/register")
    fun register(@RequestBody registerRequest: AuthenticationRegisterRequest): ResponseEntity<*> {
        if (userService.findByNickname(registerRequest.nickname) != null) return ResponseEntity.badRequest().build<User>()

        val encryptedPassword = BCryptPasswordEncoder().encode(registerRequest.password)
        val user = User(
            nickname = registerRequest.nickname,
            password = encryptedPassword
        )

        val createdUser = userService.save(user)

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(createdUser)
    }

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: AuthenticationLoginRequest): ResponseEntity<*> {
        val usernamePassword = UsernamePasswordAuthenticationToken(
            loginRequest.nickname,
            loginRequest.password
        )

        val auth = authenticationManager.authenticate(usernamePassword)

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(auth)
    }

}
