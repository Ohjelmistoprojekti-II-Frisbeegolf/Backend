package hh.project.discgolf.controllers

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.http.MediaType

@SpringBootTest
@AutoConfigureMockMvc
internal class HoleControllerTest @Autowired constructor(
    val mockMvc: MockMvc
) {

    @Test
    fun `should return all holes`() {
        mockMvc.get("/holes")
            .andDo { print() }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
            }
    }
}