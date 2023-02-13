package hh.project.discgolf.controllers

import hh.project.discgolf.entities.Game
import hh.project.discgolf.services.GameService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
class GameController(private val gameService: GameService) {

    @GetMapping(value = ["/games"])
    fun getAllGames(): Iterable<Game> = gameService.getAllGames()
}