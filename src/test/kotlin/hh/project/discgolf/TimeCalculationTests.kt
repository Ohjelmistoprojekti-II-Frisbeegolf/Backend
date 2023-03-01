package hh.project.discgolf

import hh.project.discgolf.services.UserService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.assertj.core.api.Assertions.*

@SpringBootTest
@AutoConfigureMockMvc
class TimeCalculationTests
    @Autowired constructor(
    val userService: UserService
)

// 1 hour = 3 600 seconds.

{
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
}