package hh.project.discgolf.services

import hh.project.discgolf.entities.Game
import hh.project.discgolf.repositories.GameRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service

@Service
class GameService(private val gameRepository: GameRepository) {


    fun getAllGames(): List<Game> = gameRepository.findAll()

    fun getGameById(gameId: Long): Game =
        gameRepository.findById(gameId)
        .orElseThrow { NoSuchElementException("Game with given id not present!")}

    fun createGame(game: Game): Game = gameRepository.save(game)

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
}