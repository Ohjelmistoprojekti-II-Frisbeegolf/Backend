package hh.project.discgolf.services

import hh.project.discgolf.entities.Game
import hh.project.discgolf.repositories.GameRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.util.*

@Service
class GameService(private val gameRepository: GameRepository) {

    fun getAllGames(): List<Game> = gameRepository.findAll()

    fun getGameById(gameId: Long): Game =
        gameRepository.findById(gameId)
        .orElseThrow()

    fun createGame(game: Game): Game = gameRepository.save(game)
}