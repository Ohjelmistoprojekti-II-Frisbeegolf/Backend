package hh.project.discgolf.repositories

import hh.project.discgolf.entities.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
}