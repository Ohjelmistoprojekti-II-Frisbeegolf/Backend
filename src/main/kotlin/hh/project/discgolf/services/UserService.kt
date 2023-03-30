package hh.project.discgolf.services

import hh.project.discgolf.entities.User
import hh.project.discgolf.enums.ScoringSystem
import hh.project.discgolf.repositories.UserRepository
import jakarta.validation.Valid
import org.springframework.stereotype.Service
import org.springframework.validation.BindingResult

@Service
class UserService (private val userRepository: UserRepository){

    fun getAllUsers(): List<User> = userRepository.findAll()

    fun getUserById(userId: Long) : User {
        if (userRepository.findById(userId).isPresent) {
            val user = userRepository.findById(userId).get()
            if (user.games.isNotEmpty()) {
                user.gamesPlayed = user.games.size
                user.totalTimePlayed = formatTotalTimePlayed(userRepository.totalTimePlayed(userId)?:0)
                user.totalThrowsThrown = userRepository.getTotalThrowsThrown(userId)
                user.totalSteps = userRepository.getStepsForUser(userId)?: 0
                user.results = generateMapOfResults(userId)
            }
            return user
        } else throw NoSuchElementException("User doesn't exists with given id!")
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
                    password = user.password,
                    role = user.role
                )
            )
        } else throw NoSuchElementException("User doesn't exist with give id!")
    }

    /**
     @param how many seconds user has played.
     @return formatted string.
      *
      * Example: If the total played time is 4 028 seconds
      * the return value is 01:07:08.
     */
     fun formatTotalTimePlayed(totalTimePlayedInSeconds: Long): String {
         val wantedFormat = "%02d" // 1 -> 01.
         val hours = calculateHours(totalTimePlayedInSeconds)
         val minutes = calculateMinutes(totalTimePlayedInSeconds)
         val seconds = calculateSeconds(totalTimePlayedInSeconds)
         return "${wantedFormat.format(hours)}:${wantedFormat.format(minutes)}:${wantedFormat.format(seconds)}"
    }

    fun calculateHours(totalTimePlayedInSeconds: Long) = totalTimePlayedInSeconds / 3_600

    fun calculateMinutes(totalTimePlayedInSeconds: Long) = (totalTimePlayedInSeconds % 3_600) / 60

    fun calculateSeconds(totalTimePlayedInSeconds: Long) = totalTimePlayedInSeconds % 60

    // This one-liner code was made with the help of chat-gtp.
    fun generateMapOfResults(userId: Long) : Map<ScoringSystem, Int> = ScoringSystem.values().associateWith { it.getResults(userId, userRepository) }

}

