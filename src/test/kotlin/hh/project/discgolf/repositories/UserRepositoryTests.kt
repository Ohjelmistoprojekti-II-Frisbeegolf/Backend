package hh.project.discgolf.repositories

import hh.project.discgolf.entities.Game
import org.assertj.core.api.Assertions.*
import hh.project.discgolf.entities.User
import hh.project.discgolf.repositories.UserRepository
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
    val gameRepository: GameRepository
) {

    @BeforeEach
    fun init() {
        userRepository.deleteAll()
        val user1 = User(userId = 1L, username = "Matti", email = "matti@mail.com", password = "df68327ghj")
        val user2 = User(userId = 2L, username = "Maija", email = "maija@mail.com", password = "jdncs788ds")
        userRepository.saveAll(listOf(user1, user2))
    }

    @Test
    fun `should save a user`() {
        val newUser = User(userId = 3L, username = "Ville", email = "v@mail.com", password = "hjasasjf23")
        userRepository.save(newUser)
        assertThat(userRepository.findById(3L)).isNotNull
    }

    @Test
    fun `should return all users`() {
        val users = userRepository.findAll()
        assertThat(users).isNotEmpty
        assertThat(users).hasSize(2)
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
        val user = User(username = "user1", password = "password", email = "email1@email.com")
        val savedUser = userRepository.save(user)
        assertThat(userRepository.getStepsForUser(savedUser.userId)).isNull()
        assertThat(userRepository.getStepsForUser(savedUser.userId)?:0).isEqualTo(0)
    }

    @Test
    fun`user haves one game with 222 steps - should return 222`() {
        val user = User(username = "user2", password = "password", email = "email2@email.com")
        val savedUser = userRepository.save(user)
        val game = Game(steps = 222, user = savedUser)
        gameRepository.save(game)
        assertThat(userRepository.getStepsForUser(savedUser.userId)).isEqualTo(222)
    }

    @Test
    fun`user haves two games, one with 222 steps, and one with 8 000 steps - should return 8 222`() {
        val user = User(username = "user3", password = "password", email = "email3@email.com")
        val savedUser = userRepository.save(user)
        val game1 = Game(steps = 222, user = savedUser)
        val game2 = Game(steps = 8_000, user = savedUser)
        gameRepository.save(game1)
        gameRepository.save(game2)
        assertThat(userRepository.getStepsForUser(savedUser.userId)).isEqualTo(8_222)
    }
    @Test
    fun `totalTimePlayed returns a correct value`() {
        val newUser = User(username = "user4", password = "password", email = "mail22@hotmail.com")
        val saveUser = userRepository.save(newUser)
        val newGame = Game(
            startingDatetime = LocalDateTime.now(),
            endingDatetime = LocalDateTime.now().plusMinutes(120),
            user = saveUser)
        gameRepository.save(newGame)
        assertThat(userRepository.totalTimePlayed(saveUser.userId)).isEqualTo(7_200L)
    }
}



