package com.xboxgamecollection.api.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTCreationException
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Service
import java.util.Date

@Service
@EnableConfigurationProperties(JwtProperties::class)
class TokenService(
    private val jwtProperties: JwtProperties
) {

    fun generateAccessToken(user: User): String {
        try {
            val algorithm = Algorithm.HMAC256(jwtProperties.key);
            val accessToken = JWT.create()
                .withSubject(user.username)
                .withIssuedAt(Date(System.currentTimeMillis()))
                .withExpiresAt(generateExpirationDate())
                .sign(algorithm)

            return accessToken
        } catch (exception: JWTCreationException) {
            throw RuntimeException("Error while generation token", exception)
        }
    }

    fun generateExpirationDate(): Date =
        Date(System.currentTimeMillis() + jwtProperties.accessTokenExpiration)

    fun extractSubject(token: String): String {
        val algorithm = Algorithm.HMAC256(jwtProperties.key);

        val tokenSubject = JWT.require(algorithm)
            .build()
            .verify(token)
            .subject

        return tokenSubject
    }
}
