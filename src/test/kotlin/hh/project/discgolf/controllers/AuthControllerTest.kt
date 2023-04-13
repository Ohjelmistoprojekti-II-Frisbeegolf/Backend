package hh.project.discgolf.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import hh.project.discgolf.dto.LoginCredentials
import hh.project.discgolf.dto.NewUserValidation
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import kotlin.reflect.typeOf

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper
) {

    @Test
    fun `should return token when users logs in`() {
        val userCredentials = LoginCredentials(username = "Maija", password = "salasana")

        val performPost = mockMvc.post("/login") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(userCredentials)
        }
        performPost
            .andDo { print() }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.token") { typeOf<String>() }
            }
    }

    @Test
    fun `should return bad request if no user credentials given`() {

        val performPost = mockMvc.post("/login") {
            contentType = MediaType.APPLICATION_JSON
        }
        performPost
            .andDo { print() }
            .andExpect {
                status { isBadRequest() }
            }
    }

    @Test
    fun `should return forbidden if username id empty`() {
        val userCredentials = LoginCredentials(username = "", password = "salasana")

        val performPost = mockMvc.post("/login") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(userCredentials)
        }

        performPost
            .andDo { print() }
            .andExpect {
                status { isUnauthorized() }
                jsonPath("$") { value("Wrong credentials.")}
            }
    }

    @Test
    fun `should return forbidden if user credentials are invalid`() {
        val userCredentials = LoginCredentials(username = "Heikki", password = "salasan")

        val performPost = mockMvc.post("/login") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(userCredentials)
        }

        performPost
            .andDo { print() }
            .andExpect {
                status { isUnauthorized() }
                jsonPath("$") { value("Wrong credentials.")}
            }
    }

    @Test
    fun `should return ok if passwords match`() {
        val userValidation = NewUserValidation(username = "Maikki", password = "salasana", passwordCheck = "salasana")

        val performPost = mockMvc.post("/register") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(userValidation)
        }
        performPost
            .andDo { print() }
            .andExpect {
                status { isOk() }
            }
    }

    @Test
    fun `should return bad request id passwords don't match`() {
        val userValidation = NewUserValidation(username = "Marko", password = "salasana", passwordCheck = "salasanaa")

        val performPost = mockMvc.post("/register") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(userValidation)
        }
        performPost
            .andDo { print() }
            .andExpect {
                status { isUnauthorized() }
                jsonPath("$") { value("Don't match")}
            }
    }
}