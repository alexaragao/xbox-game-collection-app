package com.xboxgamecollection.api.service

import com.xboxgamecollection.api.model.User
import com.xboxgamecollection.api.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {

    fun findAll(): List<User> = userRepository.findAll()

    fun findById(id: Long): User? = userRepository.findById(id).orElse(null)

    fun findByNickname(nickname: String): User? = userRepository.findByNickname(nickname)

    fun save(user: User): User = userRepository.save(user)

    fun deleteById(id: Long) = userRepository.deleteById(id)
}
