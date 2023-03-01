package hh.project.discgolf.repositories

import hh.project.discgolf.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserRepository : JpaRepository<User, Long> {

    fun findByUsername(username : String) : User

    @Query("SELECT EXTRACT(EPOCH FROM (ending_datetime - starting_datetime)) FROM Game WHERE user_id = :userId", nativeQuery = true)
    fun totalTimePlayed(userId : Long) : List<Long>

    @Query("SELECT COUNT(s.score)" +
            "FROM STROKE s " +
            "JOIN GAME g on s.game_id = g.game_id " +
            "JOIN USERS u on g.user_id = u.user_id " +
            "WHERE u.user_id = :userId",
            nativeQuery = true
    )
    fun getTotalThrowsThrown(userId : Long) : Int

    @Query("SELECT SUM(steps) FROM GAME WHERE user_id = :userId", nativeQuery = true)
    fun getStepsForUser(userId: Long) : Int? = 0

    @Query("SELECT COUNT(s.score) " +
            "FROM STROKE s " +
            "JOIN HOLE h ON s.hole_id = h.hole_id " +
            "JOIN GAME g ON h.course_id = g.course_id " +
            "JOIN USERS u ON g.user_id = u.user_id " +
            "WHERE s.score - h.hole_par = :score AND u.user_id = :userId AND s.score > 1",
            nativeQuery = true
    )
    fun getScores(score : Int, userId: Long) : Int

    @Query("SELECT COUNT(s.score) " +
            "FROM STROKE s " +
            "JOIN HOLE h ON s.hole_id = h.hole_id " +
            "JOIN GAME g ON h.course_id = g.course_id " +
            "JOIN USERS u ON g.user_id = u.user_id " +
            "WHERE s.score = 1 AND u.user_id = :userId",
        nativeQuery = true
    )
    fun getAces(userId: Long) : Int

    @Query("SELECT COUNT(s.score) " +
            "FROM STROKE s " +
            "JOIN HOLE h ON s.hole_id = h.hole_id " +
            "JOIN GAME g ON h.course_id = g.course_id " +
            "JOIN USERS u ON g.user_id = u.user_id " +
            "WHERE s.score - h.hole_par > 3 AND u.user_id = :userId",
        nativeQuery = true
    )
    fun getOverTripleBogeys(userId: Long) : Int



}