package hh.project.discgolf.repositories

import hh.project.discgolf.entities.Game
import org.springframework.data.jpa.repository.JpaRepository

interface GameRepository : JpaRepository<Game, Long> {

}