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
        user.gamesPlayed = user.games.size
        user.totalTimePlayed = formatTotalTimePlayed(userRepository.totalTimePlayed(userId))
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

     fun formatTotalTimePlayed(listOfDurationsInSeconds: List<Long>): String {
        val totalTimePlayedInSeconds = calculateTotalTimePlayed(listOfDurationsInSeconds)
        val calculatedTimeInString = calculateTime(totalTimePlayedInSeconds).split(",")
        return "%02d".format(calculatedTimeInString[0].toInt()) + ":" + "%02d".format(calculatedTimeInString[1].toInt()) + ":" + "%02d".format(calculatedTimeInString[2].toInt())
    }
    private fun calculateTotalTimePlayed( listOfEpochs: List<Long>) : Long {
        return listOfEpochs.sum()
    }

    fun calculateTime(totalTimePlayedInSeconds : Long) : String {
        val hours = totalTimePlayedInSeconds / 3_600
        val minutes = (totalTimePlayedInSeconds % 3_600) / 60
        val seconds = totalTimePlayedInSeconds % 60
        return "$hours,$minutes,$seconds"
    }
}

