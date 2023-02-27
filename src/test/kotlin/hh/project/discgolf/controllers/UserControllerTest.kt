package hh.project.discgolf.controllers

import hh.project.discgolf.entities.User
import hh.project.discgolf.repositories.UserRepository
import org.hamcrest.Matchers.hasSize
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureMockMvc
internal class UserControllerTest
    @Autowired constructor (
        val mockMvc: MockMvc,
        val userRepository: UserRepository
)

{
    @Test
    fun `should return all users`() {
        val quantityOfUsers = userRepository.findAll().size
        mockMvc.get("/users")
            .andDo { print() }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
            jsonPath("$", hasSize<Array<User>>(quantityOfUsers))
            }
    }

    @Test
    fun `should return user by given id`() {
        val userId = 1L
        val user = userRepository.findById(userId).get()

        mockMvc.get("/users/$userId")
            .andDo { print() }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.userId") {value(userId)}
                jsonPath("$.username") {value(user.username)}
            }
    }

    @Test
    fun `should return is not found -status with id that doesn't exist`() {
        val incorrectId = -1L

        mockMvc.get("/users/$incorrectId")
            .andDo { print() }
            .andExpect {
                status { isNotFound() }
            }
    }
}