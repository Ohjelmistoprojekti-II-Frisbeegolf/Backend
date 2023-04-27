package hh.project.discgolf.services

import hh.project.discgolf.entities.Game
import hh.project.discgolf.entities.Stroke
import hh.project.discgolf.entities.User
import hh.project.discgolf.repositories.CourseRepository
import hh.project.discgolf.repositories.GameRepository
import hh.project.discgolf.repositories.StrokeRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class GameService(private val gameRepository: GameRepository, private val strokeRepository: StrokeRepository) {


    fun getAllGames(): List<Game> = gameRepository.findAll()

    fun getGameById(gameId: Long): Game =
        gameRepository.findById(gameId)
        .orElseThrow { NoSuchElementException("Game with given id not present!")}

    fun createGame(game: Game, authentication: Authentication) {
        val user = authentication.principal as User
        val newGame = Game()
        newGame.user = user
        newGame.course = game.course
        newGame.endingDatetime = LocalDateTime.now()
        val savedGame = gameRepository.save(newGame)
        saveStrokesToGame(savedGame, game.strokes)
        println(game.strokes[3].score)
    }

    private fun saveStrokesToGame(game : Game, strokes : List<Stroke>) {
        strokes.forEach{
            val stroke = Stroke()
            stroke.game = game
            stroke.score = it.score
            stroke.hole = it.hole
            strokeRepository.save(stroke)
             }
    }

    fun deleteGame(gameId: Long) {


        return if (gameRepository.existsById(gameId)) {
            gameRepository.deleteById(gameId)
        } else throw NoSuchElementException("User doesn't exist with given id!")
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
}
