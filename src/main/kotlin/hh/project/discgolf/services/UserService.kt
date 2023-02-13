package hh.project.discgolf.services

import hh.project.discgolf.entities.User
import hh.project.discgolf.repositories.UserRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service

@Service
class UserService (private val userRepository: UserRepository){

    fun getAllUsers(): List<User> = userRepository.findAll()

    fun getUserById(id: Long): User =
        userRepository.findById(id)
            .orElseThrow()

    fun createNewUser(user: User): User = userRepository.save(user)

    fun deleteUser(id: Long) {
        return if (userRepository.existsById(id)) {
            userRepository.deleteById(id)
        } else throw NotFoundException()
    }

    fun updateUser(id: Long, user: User): User {
        return if (userRepository.existsById(id)) {
            userRepository.save(
                User(
                    userId = id,
                    games = user.games,
                    username = user.username,
                    email = user.email,
                    password = user.password,
                    role = user.role
                )
            )
        } else throw NotFoundException()
    }
}