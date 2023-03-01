package hh.project.discgolf.repositories

import hh.project.discgolf.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserRepository : JpaRepository<User, Long> {

    fun findByUsername(username : String) : User

    @Query("SELECT EXTRACT(EPOCH FROM (ending_datetime - starting_datetime)) FROM Game WHERE user_id = :userId", nativeQuery = true)
    fun totalTimePlayed(userId : Long) : List<Long>

    @Query("SELECT SUM(s.score)" +
            "FROM STROKE s " +
            "JOIN GAME g on s.game_id = g.game_id " +
            "JOIN USERS u on g.user_id = u.user_id " +
            "WHERE u.user_id = :userId",
            nativeQuery = true
    )
    fun getTotalThrowsThrown(userId : Long) : Int? = 0
}