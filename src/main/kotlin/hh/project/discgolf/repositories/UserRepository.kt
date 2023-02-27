package hh.project.discgolf.repositories

import hh.project.discgolf.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserRepository : JpaRepository<User, Long> {

    fun findByUsername(username : String) : User

    @Query("SELECT COUNT(user_id) FROM Game WHERE user_id = :userId", nativeQuery = true)
    fun gameAmountForUser(userId : Long) : Int
}