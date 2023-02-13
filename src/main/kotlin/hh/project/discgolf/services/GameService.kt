package hh.project.discgolf.services

import hh.project.discgolf.entities.Game
import hh.project.discgolf.repositories.GameRepository
import org.springframework.stereotype.Service

@Service
class GameService(private val gameRepository: GameRepository) {

    fun getAllGames(): Iterable<Game> = gameRepository.findAll()
}