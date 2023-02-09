package hh.project.discgolf.repositories

import hh.project.discgolf.entities.Game
import org.springframework.data.repository.CrudRepository

interface GameRepository : CrudRepository<Game, Long> {
}