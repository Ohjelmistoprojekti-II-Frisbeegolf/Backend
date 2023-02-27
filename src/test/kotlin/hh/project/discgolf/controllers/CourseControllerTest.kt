package hh.project.discgolf.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import hh.project.discgolf.entities.Course
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
internal class CourseControllerTest @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper
) {
    @Test
    fun `should return all courses`() {
        mockMvc.get("/courses")
            .andDo { print() }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$[0].courseId") { value(1) }
            }
    }

    @Test
    fun `should return course by given id`() {
        val id = 2L

        mockMvc.get("/courses/$id")
            .andDo { print() }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.courseId") { value(2) }
                jsonPath("$.courseName") { value("Oittaa Kalliometsä") }
            }
    }

    @Test
    fun `should return not found with id that doesn't exist`() {
        val incorrectId = -1L

        mockMvc.get("/courses/$incorrectId")
            .andDo { print() }
            .andExpect {
                status { HttpStatus.NOT_FOUND }
            }
    }

    @Test
    fun `should return saved course`() {
        val newCourse = Course(courseName = "Golf-kenttä")

        val performPost = mockMvc.post("/courses") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(newCourse)
        }
        performPost
            .andDo { print() }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.courseName") { value("Golf-kenttä") }
            }
    }

    @Test
    fun `should return error if courseName already exists`() {
        val invalidCourse = Course(courseName = "Talin frisbeegolfpuisto")

        val performPost = mockMvc.post("/courses") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(invalidCourse)
        }
        performPost
            .andDo { print() }
            .andExpect {
                status { isBadRequest() }
            }
    }
}
