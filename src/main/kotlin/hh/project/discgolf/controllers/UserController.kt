package hh.project.discgolf.controllers

import hh.project.discgolf.entities.User
import hh.project.discgolf.services.UserService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController


@RestController
@CrossOrigin
class UserController(private val userService: UserService) {

    @GetMapping(value = ["/users"])
    fun getAllUsers(): List<User> = userService.getAllUsers()

    @GetMapping(value = ["/users/{id}"])
    fun getUserById(@PathVariable("id") id: Long): User =
        userService.getUserById(id)
}