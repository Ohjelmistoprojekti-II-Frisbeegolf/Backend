package hh.project.discgolf.services

import hh.project.discgolf.entities.User
import hh.project.discgolf.enums.ScoringSystem
import hh.project.discgolf.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService (private val userRepository: UserRepository){

    fun getAllUsers(): List<User> = userRepository.findAll()

    fun getUserById(userId: Long) : User {
        if (userRepository.findById(userId).isPresent) {
            val user = userRepository.findById(userId).get()
            user.gamesPlayed = user.games.size
            if (user.games.isNotEmpty()) {
                user.totalTimePlayed = formatTotalTimePlayed(userRepository.totalTimePlayed(userId)?:0)
                user.totalThrowsThrown = userRepository.getTotalThrowsThrown(userId)
                user.totalSteps = userRepository.getStepsForUser(userId)?: 0
                user.results = generateLinkedHashMapOfResults(userId)
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
                    email = user.email,
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

    fun generateLinkedHashMapOfResults(userId: Long) : LinkedHashMap<ScoringSystem, Int> {
        val linkedHashMapOfResults = LinkedHashMap<ScoringSystem, Int>()
        linkedHashMapOfResults[ScoringSystem.ACE] = userRepository.getAces(userId)
        for (result in -3..3) {
            when (result) {
                -3 -> linkedHashMapOfResults[ScoringSystem.ALBATROSS] = userRepository.getResults(result, userId)
                -2 -> linkedHashMapOfResults[ScoringSystem.EAGLE] = userRepository.getResults(result, userId)
                -1 -> linkedHashMapOfResults[ScoringSystem.BIRDIE] = userRepository.getResults(result, userId)
                0 -> linkedHashMapOfResults[ScoringSystem.PAR] = userRepository.getResults(result, userId)
                1 -> linkedHashMapOfResults[ScoringSystem.BOGEY] = userRepository.getResults(result, userId)
                2 -> linkedHashMapOfResults[ScoringSystem.DOUBLE_BOGEY] = userRepository.getResults(result, userId)
                3 -> linkedHashMapOfResults[ScoringSystem.TRIPLE_BOGEY] = userRepository.getResults(result, userId)
            }
        }
        linkedHashMapOfResults[ScoringSystem.OVER_TRIPLE_BOGEY] = userRepository.getOverTripleBogeys(userId)
        return linkedHashMapOfResults
    }
}

