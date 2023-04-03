package hh.project.discgolf.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import hh.project.discgolf.dto.NewUserValidation
import hh.project.discgolf.entities.User
import hh.project.discgolf.enums.UserRole
import hh.project.discgolf.repositories.UserRepository
import hh.project.discgolf.services.TokenService
import org.hamcrest.Matchers.hasSize
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
internal class UserControllerTest
    @Autowired constructor (
        val mockMvc: MockMvc,
        val userRepository: UserRepository,
        val objectMapper: ObjectMapper,
        val tokenService: TokenService
) {

    val token = tokenService.createToken(userRepository.findByUsername("Keijo").get())

    @Test
    fun `should return all users`() {
        val quantityOfUsers = userRepository.findAll().size

        mockMvc.get("/users") {
            header("Authorization", "Bearer $token")
        }
            .andDo { print() }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$", hasSize<Array<User>>(quantityOfUsers))
            }
    }

    @Test
    fun `should return authenticated user`() {

        mockMvc.get("/users/current") {
            header("Authorization", "Bearer $token")
        }
            .andDo { print() }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
            }
    }

    @Test
    fun `should return is unauthorized if not logged in`() {

        mockMvc.get("/users/current")
            .andDo { print() }
            .andExpect {
                status { isUnauthorized() }
            }
    }

    @Test
    fun `save new user with good credentials - Should return isOk()`() {
        val userValidation = NewUserValidation(username = "Meijo", password = "salasana", passwordCheck = "salasana")

        val performPost = mockMvc.post("/register") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(userValidation)
        }
        performPost
            .andExpect {
                status { isOk() }
            }
    }

    @Test
    fun `saving new user with already taken username - Should return isBadRequest()`() {
        val userValidation = NewUserValidation(username = "Keijo", password = "salasana", passwordCheck = "salasana")

        val performPost = mockMvc.post("/register") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(userValidation)
        }
        performPost
            .andDo { print() }
            .andExpect {
                status { isBadRequest() }
                jsonPath("$") { value("Username is already in use!")}
            }
    }


    @Test
    fun `saving new user without an username - Should return isBadRequest()`() {
        val newUser = User(password = "password", role = UserRole.USER)

        val performPost = mockMvc.post("/register") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(newUser)
        }
        performPost
            .andExpect {
                status { isBadRequest() }
            }

    }

    @Test
    fun `saving new user without a password - Should return isBadRequest()`() {
        val newUser = User(username = "Keijo1", role = UserRole.USER)

        val performPost = mockMvc.post("/register") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(newUser)
        }
        performPost
            .andExpect {
                status { isBadRequest() }
            }

    }
}