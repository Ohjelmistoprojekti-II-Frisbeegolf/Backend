package hh.project.discgolf.services

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@AutoConfigureMockMvc
class UserServiceTest
    @Autowired constructor(
        val userService: UserService
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
}