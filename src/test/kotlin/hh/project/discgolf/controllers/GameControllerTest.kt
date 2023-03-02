package hh.project.discgolf.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import hh.project.discgolf.entities.Game
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.delete
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
internal class GameControllerTest
    @Autowired constructor(
        val mockMvc: MockMvc,
        val objectMapper: ObjectMapper
    )
{
        @Test
        fun `should return all games`() {
            mockMvc.get("/games")
                .andDo{print()}
                .andExpect{
                    status{isOk()}
                    content{contentType(MediaType.APPLICATION_JSON)}
                    jsonPath("$[0].gameId") {value(1)}
                }
        }
        @Test
        fun `should return game by given id`() {
            val id = 2L

            mockMvc.get("/games/$id")
                .andDo { print() }
                .andExpect {
                    status{isOk()}
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.gameId") {value(2)}
                    jsonPath("$.steps") {value(8000)}
                }
        }
        @Test
        fun `should return not found if the game with that id doesn't exist`() {
            val incorrectId = -1L

            mockMvc.get("/games/$incorrectId")
                .andDo { print() }
                .andExpect {
                    status { isNotFound() }
                }
        }
        @Test
        fun `should return a saved game`() {
            val newGame = Game(steps = 7000, gameId = 6L)

            val performPost = mockMvc.post("/games") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newGame)
            }
            performPost
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.steps") {value(7000)}
                }
        }
        @Test
        fun `should delete a game by gameId`() {
            val givenGameId = 1L

                mockMvc.delete("/games/$givenGameId")
                    .andDo{print()}
                    .andExpect { status {isOk() }
                mockMvc.get("/games/$givenGameId")
                    .andExpect{status{isNotFound()}}
            }
        }
}