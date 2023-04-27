package hh.project.discgolf.repositories

import hh.project.discgolf.entities.Game
import hh.project.discgolf.entities.Stroke
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface StrokeRepository : JpaRepository<Stroke, Long> {

    fun findByGame(game: Game): List<Stroke>
}