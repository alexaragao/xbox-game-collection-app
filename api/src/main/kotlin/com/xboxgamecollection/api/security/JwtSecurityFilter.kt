package com.xboxgamecollection.api.security

import com.xboxgamecollection.api.service.AuthenticationService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtSecurityFilter(
    private val authenticationService: AuthenticationService,
    private val tokenService: TokenService
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val accessToken = recoverAccessToken(request)

        if (accessToken != null) {
            val subject = tokenService.extractSubject(accessToken)
            val userDetails = authenticationService.loadUserByUsername(subject)

            val authentication =
                UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)

            SecurityContextHolder.getContext().authentication = authentication
        }

        filterChain.doFilter(request, response)
    }

    fun recoverAccessToken(request: HttpServletRequest): String? {
        val authHeader = request.getHeader("Authorization") ?: return null
        val bearerToken = authHeader.replace("Bearer ", "")
        return bearerToken
    }
}
