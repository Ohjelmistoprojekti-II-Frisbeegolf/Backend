package hh.project.discgolf.controllers

import hh.project.discgolf.entities.User
import hh.project.discgolf.services.UserService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*


@RestController
@CrossOrigin
class UserController(
    private val userService: UserService
) {

    @GetMapping(value = ["/users"])
    fun getAllUsers(): List<User> = userService.getAllUsers()

    @GetMapping(value = ["/users/current"])
    fun getUser(authentication: Authentication): User = userService.getUser(authentication)

    @PutMapping(value = ["/users/{id}"])
    fun updateUser(@PathVariable("id") id: Long, @RequestBody payload: User): User = userService.updateUser(id, payload)

    @DeleteMapping(value = ["/users/{id}"])
    @PreAuthorize("hasRole('ADMIN')")
    fun deleteUser(@PathVariable("id") id: Long) = userService.deleteUser(id)
}