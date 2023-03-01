package hh.project.discgolf.services

import hh.project.discgolf.entities.Game
import hh.project.discgolf.entities.User
import hh.project.discgolf.repositories.GameRepository
import hh.project.discgolf.repositories.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@AutoConfigureMockMvc
class UserServiceTest
    @Autowired constructor(
        val userService: UserService,
        val userRepository: UserRepository,
        val gameRepository: GameRepository
    )

{
    /*
     * ----------------- Testing formatTotalTimePlayed() function ----------------
     */

    @Test
    fun `Every value is 0`() {
        val correctValue = "00:00:00"
        val valueFromFunction = userService.formatTotalTimePlayed(listOf(0L))
        assertThat(valueFromFunction).isEqualTo(correctValue)
    }

    @Test
    fun `Over zero hours but under ten hours - other values are zeros`() {
        val correctValue = "02:00:00"
        val valueFromFunction = userService.formatTotalTimePlayed(listOf(7_200L))
        assertThat(valueFromFunction).isEqualTo(correctValue)
    }

    @Test
    fun `Over ten hours - other values are zeros`() {
        val correctValue = "13:00:00"
        val valueFromFunction = userService.formatTotalTimePlayed(listOf(46_800L))
        assertThat(valueFromFunction).isEqualTo(correctValue)
    }

    @Test
    fun `Over zero minutes but under ten minutes - other values are zeros`() {
        val correctValue = "00:08:00"
        val valueFromFunction = userService.formatTotalTimePlayed(listOf(480L))
        assertThat(valueFromFunction).isEqualTo(correctValue)
    }

    @Test
    fun `Over ten minutes - other values are zeros`() {
        val correctValue = "00:11:00"
        val valueFromFunction = userService.formatTotalTimePlayed(listOf(660L))
        assertThat(valueFromFunction).isEqualTo(correctValue)
    }

    @Test
    fun `Over zero seconds but under ten seconds - other values are zeros`() {
        val correctValue = "00:00:01"
        val valueFromFunction = userService.formatTotalTimePlayed(listOf(1L))
        assertThat(valueFromFunction).isEqualTo(correctValue)
    }

    @Test
    fun `Over ten seconds - other values are zeros`() {
        val correctValue = "00:00:11"
        val valueFromFunction = userService.formatTotalTimePlayed(listOf(11L))
        assertThat(valueFromFunction).isEqualTo(correctValue)
    }

    /*
     * ----------------- Testing time calculation functions -----------------
     */

    @Test
    fun`should return zero hours with 3 599 seconds as an input`() {
        assertThat(userService.calculateHours(3_599L)).isEqualTo(0L)
    }

    @Test
    fun`should return one hour with 3 600 seconds as an input`() {
        assertThat(userService.calculateHours(3_600L)).isEqualTo(1L)
    }

    @Test
    fun`should return one hour with 3 601 seconds as an input`() {
        assertThat(userService.calculateHours(3_601L)).isEqualTo(1L)
    }
    @Test
    fun`should return zero minutes with 59 seconds as an input `() {
        assertThat(userService.calculateMinutes(59L)).isEqualTo(0L)
    }

    @Test
    fun`should return one minute with 60 seconds as an input `() {
        assertThat(userService.calculateMinutes(60L)).isEqualTo(1L)
    }

    @Test
    fun`should return one minute with 61 seconds as an input `() {
        assertThat(userService.calculateMinutes(61L)).isEqualTo(1L)
    }

    @Test
    fun`should return zero seconds with zero seconds as an input `() {
        assertThat(userService.calculateSeconds(0L)).isEqualTo(0L)
    }
    @Test
    fun`should return zero seconds with 60 seconds as an input `() {
        assertThat(userService.calculateSeconds(60L)).isEqualTo(0L)
    }

    @Test
    fun`should return one second with 61 seconds as an input `() {
        assertThat(userService.calculateSeconds(61L)).isEqualTo(1L)
    }

    /*
     * ----------------- Testing getStepsForUser() function -----------------
     */

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
        val user = User(username = "user", password = "password", email = "email3@email.com")
        val savedUser = userRepository.save(user)
        val game1 = Game(steps = 222, user = savedUser)
        val game2 = Game(steps = 8_000, user = savedUser)
        gameRepository.save(game1)
        gameRepository.save(game2)
        assertThat(userRepository.getStepsForUser(savedUser.userId)).isEqualTo(8_222)
    }
}