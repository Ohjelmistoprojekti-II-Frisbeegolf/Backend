package hh.project.discgolf.services

import hh.project.discgolf.entities.User
import hh.project.discgolf.repositories.UserRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service

@Service
class UserService (private val userRepository: UserRepository){

    fun getAllUsers(): List<User> = userRepository.findAll()

    fun getUserById(userId: Long) : User {
        val user = userRepository.findById(userId).get()
        user.gameAmount = userRepository.gameAmountForUser(userId)
        return user
    }

    fun createNewUser(user: User): User = userRepository.save(user)

    fun deleteUser(userId: Long) {
        return if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId)
        } else throw NoSuchElementException("User doesn't exist with given id!")
    }

    fun updateUser(userId: Long, user: User): User {
        return if (userRepository.existsById(userId)) {
            userRepository.save(
                User(
                    userId = userId,
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