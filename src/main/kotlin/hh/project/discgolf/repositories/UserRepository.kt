package hh.project.discgolf.repositories

import hh.project.discgolf.entities.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long> {
}