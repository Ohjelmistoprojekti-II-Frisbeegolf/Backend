package hh.project.discgolf.repositories

import hh.project.discgolf.entities.*
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDateTime

@ExtendWith(value = [SpringExtension::class])
@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTests @Autowired constructor(
    val userRepository: UserRepository,
    val gameRepository: GameRepository,
    val strokeRepository: StrokeRepository,
    val holeRepository: HoleRepository,
    val courseRepository: CourseRepository
) {

    @BeforeEach
    fun init() {
        userRepository.deleteAll()
        val user1 = User(username = "Matti", password = "df68327ghj")
        val user2 = User(username = "Maija", password = "jdncs788ds")
        val user3 = User(username = "user3", password = "password")
        userRepository.saveAll(listOf(user1, user2, user3))
    }

    @Test
    fun `should save a user`() {
        val savedUser = userRepository.findByUsername("user3").get()
        assertThat(userRepository.findByUsername("user3")).isPresent
    }

    @Test
    fun `should return all users`() {
        val users = userRepository.findAll()
        assertThat(users).isNotEmpty
        assertThat(users).hasSize(3)
    }

    @Test
    fun `should return user by given username`() {
        val user = userRepository.findByUsername("Matti")
        assertThat(user).isNotNull
    }

    @Test
    fun `should return user by given id`() {
        for (id in 1..userRepository.findAll().size) {
            assertThat(userRepository.findById(id.toLong())).isNotNull
        }
    }

    @Test
    fun `should delete user by given id`() {
        for (user in userRepository.findAll()) {
            userRepository.deleteById(user.userId)
            assertThat(userRepository.findById(user.userId)).isEmpty
        }
    }

    @Test
    fun`user haves zero games - DB should return null - result should be zero`() {
        val savedUser = userRepository.findByUsername("Maija").get()
        assertThat(userRepository.getStepsForUser(savedUser.userId)).isNull()
        assertThat(userRepository.getStepsForUser(savedUser.userId)?:0).isEqualTo(0)
    }

    @Test
    fun`user haves one game with 222 steps - should return 222`() {
        val savedUser = userRepository.findByUsername("Matti").get()
        val game = Game(steps = 222, user = savedUser)
        gameRepository.save(game)
        assertThat(userRepository.getStepsForUser(savedUser.userId)).isEqualTo(222)
    }

    @Test
    fun`user haves two games, one with 222 steps, and one with 8 000 steps - should return 8 222`() {
        val savedUser = userRepository.findByUsername("Maija").get()
        val game1 = Game(steps = 222, user = savedUser)
        val game2 = Game(steps = 8_000, user = savedUser)
        gameRepository.save(game1)
        gameRepository.save(game2)
        assertThat(userRepository.getStepsForUser(savedUser.userId)).isEqualTo(8_222)
    }
    @Test
    fun `user has played a game of 2 hours - should return 7 200 seconds`() {
        val savedUser = userRepository.findByUsername("Maija").get()
        val newGame = Game(
            startingDatetime = LocalDateTime.now(),
            endingDatetime = LocalDateTime.now().plusMinutes(120),
            user = savedUser)
        gameRepository.save(newGame)
        assertThat(userRepository.totalTimePlayed(savedUser.userId)).isEqualTo(7_200L)
    }
    @Test
    fun `user has played two games, total of 2,5 hours - should return 9 000 seconds`() {
        val savedUser = userRepository.findByUsername("Matti").get()
        val game1 = Game(
            startingDatetime =  LocalDateTime.now(),
            endingDatetime = LocalDateTime.now().plusMinutes(60),
            user = savedUser
        )
        val game2 = Game(
            startingDatetime =  LocalDateTime.now(),
            endingDatetime = LocalDateTime.now().plusMinutes(90),
            user = savedUser
        )
        gameRepository.saveAll(listOf(game1, game2))
        assertThat(userRepository.totalTimePlayed(savedUser.userId)).isEqualTo(9_000L)
    }
    @Test
    fun `user has played no games, should return null`() {
        val savedUser = userRepository.findByUsername("Maija").get()
        assertThat(userRepository.totalTimePlayed(savedUser.userId)).isEqualTo(null)
    }
    @Test
    fun `user has played a game with 5 strokes - should return 5`() {
        val savedUser = userRepository.findByUsername("user3").get()
        val newGame = Game(user = savedUser)
        val savedGame = gameRepository.save(newGame)
            for (i in 1..5) {
                strokeRepository.save(
                    Stroke(game = savedGame, score = 1)
                )}
        assertThat(userRepository.getTotalThrowsThrown(savedUser.userId)).isEqualTo(5)
    }
    @Test
    fun `user has played a game with score of 2 - should return 2`() {
        val savedUser = userRepository.findByUsername("user3").get()
        val newCourse = Course(courseName = "Golf")
        val savedCourse = courseRepository.save(newCourse)

        val newHole = Hole(course = savedCourse, holePar = 2)
        val savedHole = holeRepository.save(newHole)

        val newGame = Game(user = savedUser, course = savedCourse)
        val savedGame = gameRepository.save(newGame)

        val newStroke = Stroke(game = savedGame, score = 2, hole = savedHole)
        val savedStroke = strokeRepository.save(newStroke)

        assertThat(userRepository.getResults(2, savedUser.userId)).isEqualTo(1)
    }

}



