package com.xboxgamecollection.api.controller

import com.xboxgamecollection.api.dto.AuthenticationLoginRequest
import com.xboxgamecollection.api.dto.AuthenticationLoginResponse
import com.xboxgamecollection.api.dto.AuthenticationRegisterRequest
import com.xboxgamecollection.api.security.TokenService
import com.xboxgamecollection.api.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

typealias AppUser = com.xboxgamecollection.api.model.User

@RestController
class AuthenticationController(
    private val authenticationManager: AuthenticationManager,
    private val userService: UserService,
    private val tokenService: TokenService
) {
    @PostMapping("/register")
    fun register(@RequestBody registerRequest: AuthenticationRegisterRequest): ResponseEntity<*> {
        if (userService.findByNickname(registerRequest.nickname) != null) return ResponseEntity.badRequest()
            .build<AppUser>()

        val encryptedPassword = BCryptPasswordEncoder().encode(registerRequest.password)
        val user = AppUser(
            nickname = registerRequest.nickname,
            password = encryptedPassword
        )

        val createdUser = userService.save(user)

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(createdUser)
    }

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: AuthenticationLoginRequest): ResponseEntity<AuthenticationLoginResponse> {
        val usernamePassword = UsernamePasswordAuthenticationToken(
            loginRequest.nickname,
            loginRequest.password
        )
        val auth = authenticationManager.authenticate(usernamePassword)

        val accessToken = tokenService.generateAccessToken(auth.principal as User)
        val user = userService.findByNickname(loginRequest.nickname)

        val response = AuthenticationLoginResponse(
            user = user!!,
            accessToken = accessToken
        )

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(response)
    }

    @PostMapping("/auth")
    fun authenticateWithToken(@AuthenticationPrincipal loggedUser: User): ResponseEntity<AuthenticationLoginResponse> {
        val user = userService.findByNickname(loggedUser.username)!!
        val accessToken = tokenService.generateAccessToken(loggedUser)

        val response = AuthenticationLoginResponse(
            user = user,
            accessToken = accessToken
        )

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(response)
    }

}
