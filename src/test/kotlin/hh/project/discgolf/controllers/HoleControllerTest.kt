package hh.project.discgolf.controllers

import hh.project.discgolf.repositories.UserRepository
import hh.project.discgolf.services.TokenService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
internal class HoleControllerTest @Autowired constructor(
    val mockMvc: MockMvc,
    val tokenService: TokenService,
    val userRepository: UserRepository
) {

    @Test
    fun `should return all holes`() {
        val token = tokenService.createToken(userRepository.findByUsername("Keijo").get())
        mockMvc.get("/holes") {
            header("Authorization", "Bearer $token")
        }
            .andDo { print() }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
            }
    }
}