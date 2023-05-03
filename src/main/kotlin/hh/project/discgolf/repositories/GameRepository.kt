package hh.project.discgolf.repositories

import hh.project.discgolf.entities.Game
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface GameRepository : JpaRepository<Game, Long> {

    @Query("SELECT g.* FROM GAME g " +
            "JOIN USERS u on g.user_id = u.user_id " +
            "WHERE u.user_id = :userId",
            nativeQuery = true
    )
    fun getUserGames(userId : Long) : List<Game>
}