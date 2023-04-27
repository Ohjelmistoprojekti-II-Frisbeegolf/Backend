package hh.project.discgolf.services

import hh.project.discgolf.entities.Game
import hh.project.discgolf.entities.User
import hh.project.discgolf.repositories.GameRepository
import hh.project.discgolf.repositories.UserRepository
import jakarta.persistence.Id
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class GameService(private val gameRepository: GameRepository, private val userRepository: UserRepository) {


    fun getAllGames(): List<Game> = gameRepository.findAll()

    fun getGameById(gameId: Long): Game =
        gameRepository.findById(gameId)
        .orElseThrow { NoSuchElementException("Game with given id not present!")}

    fun createGame(game: Game): Game = gameRepository.save(game)

    fun deleteGame(gameId: Long, userId: Long, authentication: Authentication) {
        val user = authentication.principal as User
        val game = gameRepository.findById(gameId)

        if (game.isPresent && user.userId == game.get().user?.userId) {
            gameRepository.deleteById(gameId)
        } else {
            throw NoSuchElementException("Game not found or you are not authorized to delete this game")
        }
    }

    fun updateGame(gameId: Long, game: Game): Game {
        return if (gameRepository.existsById(gameId)) {
            gameRepository.save(
                Game(
                    gameId = gameId,
                    user = game.user,
                    strokes = game.strokes,
                    steps = game.steps,
                    startingDatetime = game.startingDatetime,
                    endingDatetime = game.endingDatetime
                )
            )
        } else throw NotFoundException()
    }

    fun updateEndingGame(gameId: Long): Game {
        return if (gameRepository.existsById(gameId)) {
            val updatedGame = gameRepository.findById(gameId).get()
            updatedGame.endingDatetime = LocalDateTime.now()
            gameRepository.save(updatedGame)
        } else throw NotFoundException()
    }

    fun getUserGames(userId: Long): List<Game> {
        return gameRepository.getUserGames(userId)
    }
}


