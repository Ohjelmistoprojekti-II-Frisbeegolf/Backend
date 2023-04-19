package hh.project.discgolf.controllers

import hh.project.discgolf.dto.LoginCredentials
import hh.project.discgolf.dto.LoginResponseDto
import hh.project.discgolf.dto.NewUserValidation
import hh.project.discgolf.services.AuthService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
class AuthController (val authService: AuthService ) {

    @PostMapping(value = ["/login"])
    fun login(@RequestBody loginCredentials: LoginCredentials) : ResponseEntity<Any> = authService.handleLogin(loginCredentials)

    @PostMapping(value = ["/register"])
    fun register(@RequestBody @Valid newUserValidation: NewUserValidation) = authService.handleRegister(newUserValidation)
}