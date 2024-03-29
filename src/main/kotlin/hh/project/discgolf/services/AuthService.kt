package hh.project.discgolf.services

import hh.project.discgolf.dto.LoginCredentials
import hh.project.discgolf.dto.LoginResponseDto
import hh.project.discgolf.dto.NewUserValidation
import hh.project.discgolf.entities.User
import hh.project.discgolf.enums.UserRole
import hh.project.discgolf.repositories.UserRepository
import org.hibernate.exception.DataException
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import javax.security.auth.login.CredentialException

// Link source: https://tienisto.medium.com/securing-spring-boot-with-jwt-kotlin-7b529f99ca47

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val tokenService: TokenService,
    private val hashService: HashService

) {

    fun handleLogin(loginCredentials: LoginCredentials) : ResponseEntity<Unit> {
        if (userRepository.findByUsername(loginCredentials.username).isEmpty) {
            throw CredentialException("Wrong credentials.")
        }

        val user = userRepository.findByUsername(loginCredentials.username).get()

        if (!hashService.checkBcrypt(loginCredentials.password, user.password)) {
            throw CredentialException("Wrong credentials.")
        }

        val token = tokenService.createToken(user)

        val headers = HttpHeaders().apply {
            add(HttpHeaders.AUTHORIZATION, token)
        }

        return ResponseEntity(headers, HttpStatus.OK)
    }

    fun handleRegister(newUserValidation: NewUserValidation) {
        if (userRepository.findByUsername(newUserValidation.username).isPresent) {
            throw DataIntegrityViolationException("Username is already in use!")
        }

        if (newUserValidation.password != newUserValidation.passwordCheck) {
            throw DataException("Don't match", null, null)
        }

        userRepository.save(User(
            username = newUserValidation.username,
            password = hashService.hashBcrypt(newUserValidation.password),
            role = "ROLE_USER"
        ))

    }


}