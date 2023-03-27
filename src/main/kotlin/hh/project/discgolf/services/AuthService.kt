package hh.project.discgolf.services

import hh.project.discgolf.dto.LoginCredentials
import hh.project.discgolf.dto.LoginResponseDto
import hh.project.discgolf.dto.NewUserValidation
import hh.project.discgolf.entities.User
import hh.project.discgolf.enums.UserRole
import hh.project.discgolf.repositories.UserRepository
import org.springframework.stereotype.Service

// Link source: https://tienisto.medium.com/securing-spring-boot-with-jwt-kotlin-7b529f99ca47

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val tokenService: TokenService,
    private val hashService: HashService

) {

    // TODO: Exceptions
    fun handleLogin(loginCredentials: LoginCredentials) : LoginResponseDto {
        val user = userRepository.findByUsername(loginCredentials.username)?: throw NoSuchElementException("Login failed")

        if (!hashService.checkBcrypt(loginCredentials.password, user.password)) {
            throw NoSuchElementException("Login failed.")
        }

        return LoginResponseDto(
                token = tokenService.createToken(user)
                )
    }

    // TODO: Exceptions
    fun handleRegister(newUserValidation: NewUserValidation) {
        println(newUserValidation)
        if (userRepository.findByUsername(newUserValidation.username) != null) {
            throw NoSuchElementException("All")
        }

        if (newUserValidation.password != newUserValidation.passwordCheck) {
            throw NoSuchElementException("TODO")
        }

        userRepository.save(User(
            username = newUserValidation.username,
            password = hashService.hashBcrypt(newUserValidation.password),
            role = UserRole.USER
        ))

    }


}