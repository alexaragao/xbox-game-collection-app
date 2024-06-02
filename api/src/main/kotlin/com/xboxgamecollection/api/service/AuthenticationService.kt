package com.xboxgamecollection.api.service

import com.xboxgamecollection.api.repository.UserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

typealias AppUser = com.xboxgamecollection.api.model.User

@Service
class AuthenticationService(
    private val userRepository: UserRepository,
) : UserDetailsService {

    override fun loadUserByUsername(nickname: String): UserDetails {
        val user = userRepository.findByNickname(nickname)
        return user?.mapToUserDetails() ?: throw UsernameNotFoundException("Not found!")
    }

    private fun AppUser.mapToUserDetails(): UserDetails =
        User(this.nickname, this.password, listOf())
}
