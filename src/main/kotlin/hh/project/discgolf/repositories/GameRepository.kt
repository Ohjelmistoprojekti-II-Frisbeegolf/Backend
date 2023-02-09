package hh.project.discgolf.repositories

import hh.project.discgolf.entities.Game
import hh.project.discgolf.entities.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface GameRepository : CrudRepository<Game, Long> {
    fun findByUser(user: User): Game?
}