package hh.project.discgolf.repositories

import hh.project.discgolf.entities.Game
import hh.project.discgolf.entities.User
import org.springframework.data.repository.CrudRepository

interface GameRepository : CrudRepository<Game, Long> {
    fun findByUser(user: User): Game?
}