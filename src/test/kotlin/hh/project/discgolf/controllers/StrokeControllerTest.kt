package hh.project.discgolf.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import hh.project.discgolf.entities.Stroke
import hh.project.discgolf.entities.User
import hh.project.discgolf.repositories.StrokeRepository
import hh.project.discgolf.repositories.UserRepository
import hh.project.discgolf.services.TokenService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.patch
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
internal class StrokeControllerTest @Autowired constructor(
        val mockMvc: MockMvc,
        val objectMapper: ObjectMapper,
        val strokeRepository: StrokeRepository,
        val tokenService: TokenService,
        val userRepository: UserRepository
    )
{

    @BeforeEach
    fun init() {
        userRepository.deleteAll()
        userRepository.save(User(username = "Keijo", password = "passwordfortesting", role = "ROLE_USER"))
    }

    @Test
    fun `should return all strokes`() {
        val token = tokenService.createToken(userRepository.findAll()[0])
        mockMvc.get("/strokes") {
            header("Authorization", "Bearer $token")
        }
            .andDo { print() }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
            }
    }


    @Test
    fun `should return stroke with given id`() {
        val strokeId = strokeRepository.findAll()[0].strokeId
        val token = tokenService.createToken(userRepository.findAll()[0])
        mockMvc.get("/strokes/$strokeId") {
            header("Authorization", "Bearer $token")
        }
            .andDo { print() }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.strokeId") { value(strokeId)}
            }
    }

    @Test
    fun `should return error with invalid id`() {
        val invalidId = -1L
        val token = tokenService.createToken(userRepository.findAll()[0])
        mockMvc.get("/strokes/$invalidId") {
            header("Authorization", "Bearer $token")
        }
            .andDo { print() }
            .andExpect {
                status { isNotFound() }
                jsonPath("$") { value("Stroke with given id doesn't exist!")}
            }
    }

    @Test
    fun `should create new stroke`() {
        val newStroke = Stroke(score = 3)
        val token = tokenService.createToken(userRepository.findAll()[0])
        val performPost = mockMvc.post("/strokes") {
            header("Authorization", "Bearer $token")
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(newStroke)
        }
        performPost
            .andDo { print() }
            .andExpect {
                status { isCreated() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.score") { value(3)}
            }
    }

    @Test
    fun `should update stroke score with given id and score`() {
        val strokeId = strokeRepository.findAll()[0].strokeId
        val updatedStroke = Stroke(strokeId = 1L, score = 7)
        val token = tokenService.createToken(userRepository.findAll()[0])
        val performPatch = mockMvc.patch("/strokes/$strokeId") {
            header("Authorization", "Bearer $token")
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(updatedStroke.score)
        }

        performPatch
            .andDo { print() }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.score") { value(7) }
            }
        mockMvc.get("/strokes/$strokeId"){
            header("Authorization", "Bearer $token")
        }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.score") { value(7) }
            }
    }


}