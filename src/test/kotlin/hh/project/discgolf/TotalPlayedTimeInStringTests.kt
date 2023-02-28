package hh.project.discgolf

import com.fasterxml.jackson.databind.ObjectMapper
import hh.project.discgolf.repositories.UserRepository
import hh.project.discgolf.services.UserService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.assertj.core.api.Assertions.*

@SpringBootTest
@AutoConfigureMockMvc
class CalculateTimePlayedTests
    @Autowired constructor (
        val userService: UserService
)

{
    @Test
    fun `Every value is 0`() {
        val correctValue = "00:00:00"
        val valueFromFunction = userService.generateString(listOf(0L))
        assertThat(valueFromFunction).isEqualTo(correctValue)
    }

    @Test
    fun `Over zero hours but under ten hours - other values are zeros`() {
        val correctValue = "02:00:00"
        val valueFromFunction = userService.generateString(listOf(7_200L))
        assertThat(valueFromFunction).isEqualTo(correctValue)
    }

    @Test
    fun `Over ten hours - other values are zeros`() {
        val correctValue = "13:00:00"
        val valueFromFunction = userService.generateString(listOf(46_800L))
        assertThat(valueFromFunction).isEqualTo(correctValue)
    }

    @Test
    fun `Over zero minutes but under ten minutes - other values are zeros`() {
        val correctValue = "00:08:00"
        val valueFromFunction = userService.generateString(listOf(480L))
        assertThat(valueFromFunction).isEqualTo(correctValue)
    }

    @Test
    fun `Over ten minutes - other values are zeros`() {
        val correctValue = "00:11:00"
        val valueFromFunction = userService.generateString(listOf(660L))
        assertThat(valueFromFunction).isEqualTo(correctValue)
    }

    @Test
    fun `Over zero seconds but under ten seconds - other values are zeros`() {
        val correctValue = "00:00:01"
        val valueFromFunction = userService.generateString(listOf(1L))
        assertThat(valueFromFunction).isEqualTo(correctValue)
    }

    @Test
    fun `Over ten seconds - other values are zeros`() {
        val correctValue = "00:00:11"
        val valueFromFunction = userService.generateString(listOf(11L))
        assertThat(valueFromFunction).isEqualTo(correctValue)
    }





    }