package hh.project.discgolf.controllers

import hh.project.discgolf.entities.User
import hh.project.discgolf.services.UserService
import org.springframework.web.bind.annotation.*


@RestController
@CrossOrigin
class UserController(private val userService: UserService) {

    @GetMapping(value = ["/users"])
    fun getAllUsers(): List<User> = userService.getAllUsers()

    @GetMapping(value = ["/users/{id}"])
    fun getUserById(@PathVariable("id") id: Long): User = userService.getUserById(id)

    @PostMapping(value = ["/users"])
    fun createNewUser(@RequestBody payload: User): User = userService.createNewUser(payload)

    @PutMapping(value = ["/users/{id}"])
    fun updateUser(@PathVariable("id") id: Long, @RequestBody payload: User): User = userService.updateUser(id, payload)

    @DeleteMapping(value = ["/users/{id}"])
    fun deleteUser(@PathVariable("id") id: Long) = userService.deleteUser(id)
}