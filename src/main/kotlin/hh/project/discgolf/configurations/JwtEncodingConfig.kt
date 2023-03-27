package hh.project.discgolf.configurations

import com.nimbusds.jose.jwk.source.ImmutableSecret
import com.nimbusds.jose.proc.SecurityContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder
import javax.crypto.spec.SecretKeySpec

// Link source: https://tienisto.medium.com/securing-spring-boot-with-jwt-kotlin-7b529f99ca47
@Configuration
class JwtEncodingConfig(
    private val jwtKey: String = System.getenv("SECURITY")
) {
    private val secretKey = SecretKeySpec(jwtKey.toByteArray(), "HmacSHA256")

    @Bean
    fun jwtDecoder(): JwtDecoder = NimbusJwtDecoder.withSecretKey(secretKey).build()

    @Bean
    fun jewEncoder(): JwtEncoder {
        val secret = ImmutableSecret<SecurityContext>(secretKey)
        return NimbusJwtEncoder(secret)
    }
}