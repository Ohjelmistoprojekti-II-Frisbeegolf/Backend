package hh.project.discgolf.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import hh.project.discgolf.entities.Course
import hh.project.discgolf.entities.User
import hh.project.discgolf.repositories.CourseRepository
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
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
internal class CourseControllerTest @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper,
    val tokenService: TokenService,
    val userRepository: UserRepository,
    val courseRepository: CourseRepository
) {

    @BeforeEach
    fun init() {
        userRepository.deleteAll()
        courseRepository.deleteAll()
        userRepository.save(User(username = "Keijo", password = "passwordfortesting", role = "ROLE_USER"))
        courseRepository.save(Course(courseName = "Test course"))
    }


    @Test
    fun `should return all courses`() {
        val token = tokenService.createToken(userRepository.findAll()[0])
        val courseId = courseRepository.findAll()[0].courseId
        mockMvc.get("/courses") {
            header("Authorization", "Bearer $token")
        }
            .andDo { print() }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$[0].courseId") { value(courseId) }
            }
    }

    @Test
    fun `should return course by given id`() {
        val token = tokenService.createToken(userRepository.findAll()[0])
        val id = courseRepository.findAll()[0].courseId
        mockMvc.get("/courses/$id"){
            header("Authorization", "Bearer $token")
        }
            .andDo { print() }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.courseId") { value(id) }
                jsonPath("$.courseName") { value("Test course") }
            }
    }

    @Test
    fun `should return not found with id that doesn't exist`() {
        val incorrectId = -1L
        val token = tokenService.createToken(userRepository.findAll()[0])
        mockMvc.get("/courses/$incorrectId") {
            header("Authorization", "Bearer $token")
        }
            .andDo { print() }
            .andExpect {
                status { isNotFound() }
            }
    }

    @Test
    fun `should return saved course`() {
        val token = tokenService.createToken(userRepository.findAll()[0])
        val newCourse = Course(courseName = "Golf-kenttä")
        val performPost = mockMvc.post("/courses") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(newCourse)
            header("Authorization", "Bearer $token")
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
    fun `should return course with valid courseName`() {
        val token = tokenService.createToken(userRepository.findAll()[0])
        val courseName = courseRepository.findAll()[0].courseName
        mockMvc.get("/courses/name/$courseName") {
            header("Authorization", "Bearer $token")
        }
            .andDo { print() }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.courseName") { value(courseName) }
            }
    }

    @Test
    fun `should return error if courseName already exists`() {
        val token = tokenService.createToken(userRepository.findAll()[0])
        val invalidCourse = Course(courseName = "Test course")
        val performPost = mockMvc.post("/courses") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(invalidCourse)
            header("Authorization", "Bearer $token")
        }
        performPost
            .andDo { print() }
            .andExpect {
                status { isBadRequest() }
            }
    }
}
