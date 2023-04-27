package hh.project.discgolf.controllers

import hh.project.discgolf.entities.Game
import hh.project.discgolf.entities.User
import hh.project.discgolf.services.GameService
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
class GameController(private val gameService: GameService) {

    @GetMapping(value = ["/games"])
    fun getAllGames(): List<Game> = gameService.getAllGames()

    @GetMapping(value = ["/games/{id}"])
    fun getGameById(@PathVariable("id") gameId: Long): Game = gameService.getGameById(gameId)

    @PostMapping(value = ["/games"])
    fun createGame(@RequestBody payload: Game, authentication: Authentication) = gameService.createGame(payload, authentication)

    @PatchMapping(value = ["/games/{id}"])
    fun updateGame(@PathVariable("id") id: Long): Game = gameService.updateEndingGame(id)

    @DeleteMapping(value = ["/games/{id}"])
    fun deleteGame(@PathVariable("id") id: Long, authentication: Authentication) {
        val user = authentication.principal as User
        gameService.deleteGame(id, user.userId, authentication)
    }
    @GetMapping(value = ["/games/users/{id}"])
        fun getUserGames(@PathVariable("id") userId: Long): List<Game> = gameService.getUserGames(userId)

}
