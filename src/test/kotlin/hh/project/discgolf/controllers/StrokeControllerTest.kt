package hh.project.discgolf.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import hh.project.discgolf.entities.Stroke
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.patch
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
internal class StrokeControllerTest @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper
) {

    @Test
    fun `should return all strokes`() {
        mockMvc.get("/courses")
            .andDo { print() }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
            }
    }

    @Test
    fun `should return stroke with given id`() {
        val strokeId = 1L

        mockMvc.get("/strokes/$strokeId")
            .andDo { print() }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.strokeId") { value(1L)}
            }
    }

    @Test
    fun `should return error with invalid id`() {
        val invalidId = -1L

        mockMvc.get("/strokes/$invalidId")
            .andDo { print() }
            .andExpect {
                status { isNotFound() }
                jsonPath("$") { value("Stroke with given id doesn't exist!")}
            }
    }

    @Test
    fun `should create new stroke`() {
        val newStroke = Stroke(score = 3)

        val performPost = mockMvc.post("/strokes") {
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
        val strokeId = 1L
        val updatedStroke = Stroke(strokeId = 1L, score = 7)

        val performPatch = mockMvc.patch("/strokes/$strokeId") {
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
        mockMvc.get("/strokes/$strokeId")
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.score") { value(7) }
            }
    }
}