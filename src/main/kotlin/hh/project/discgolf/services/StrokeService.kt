package hh.project.discgolf.services

import hh.project.discgolf.entities.Game
import hh.project.discgolf.entities.Stroke
import hh.project.discgolf.repositories.GameRepository
import hh.project.discgolf.repositories.StrokeRepository
import org.springframework.stereotype.Service

@Service
class StrokeService (private val strokeRepository: StrokeRepository, private val gameRepository: GameRepository) {

    fun getAllStrokes(): List<Stroke> = strokeRepository.findAll()

    fun getSingleStrokeById(strokeId: Long): Stroke =
        strokeRepository.findById(strokeId)
            .orElseThrow { NoSuchElementException("Stroke with given id doesn't exist!") }

    fun getStrokesByGame(gameId: Long): List<Stroke> {
        val game = gameRepository.findById(gameId).get()
        return strokeRepository.findByGame(game)
    }

    fun createNewStroke(stroke: Stroke): Stroke = strokeRepository.save(stroke)

    fun updateStrokeScore(strokeId: Long, newScore: Int): Stroke {
        return if (strokeRepository.existsById(strokeId)) {
            val updatedStroke: Stroke = strokeRepository.findById(strokeId).get()
            updatedStroke.score = newScore
            strokeRepository.save(updatedStroke)
        } else throw NoSuchElementException("Stroke with given id doesn't exist")
    }
}