package hh.project.discgolf.services

import hh.project.discgolf.entities.User
import hh.project.discgolf.repositories.UserRepository
import org.springframework.security.oauth2.jwt.JwsHeader
import org.springframework.security.oauth2.jwt.JwtClaimsSet
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.JwtEncoderParameters
import org.springframework.stereotype.Service
import java.lang.Exception
import java.time.Instant
import java.time.temporal.ChronoUnit

// Link source: https://tienisto.medium.com/securing-spring-boot-with-jwt-kotlin-7b529f99ca47
@Service
class TokenService(
    private val jwtDecoder: JwtDecoder,
    private val jwtEncoder: JwtEncoder,
    private val userRepository: UserRepository
) {
    fun createToken(user: User): String {
        val jwsHeader = JwsHeader.with{"HS256"}.build()
        val claims = JwtClaimsSet.builder()
            .issuedAt(Instant.now())
            .expiresAt(Instant.now().plus(90L, ChronoUnit.DAYS))
            .subject(user.username)
            .claim("userId", user.userId)
            .build()
        return jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).tokenValue
    }

    fun parseToken(token: String): User? {
        return try {
            val jwt = jwtDecoder.decode(token)
            val userId = jwt.claims["userId"] as Long
            userRepository.findById(userId).get()
        } catch (e: Exception) {
            null
        }
    }
}