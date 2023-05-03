package hh.project.discgolf.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import hh.project.discgolf.entities.Game
import hh.project.discgolf.entities.User
import hh.project.discgolf.repositories.GameRepository
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
import org.springframework.test.web.servlet.delete
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
internal class GameControllerTest @Autowired constructor(
        val mockMvc: MockMvc,
        val objectMapper: ObjectMapper,
        val tokenService: TokenService,
        val userRepository: UserRepository,
        val gameRepository: GameRepository
    )
{

    @BeforeEach
    fun `create user`() {
        userRepository.deleteAll()
        gameRepository.deleteAll()
        val savedUser = userRepository.save(User(username = "Keijo", password = "salasana", role = "ROLE_USER"))
        gameRepository.save(Game(user = savedUser))
    }




        @Test
        fun `should return all games`() {
            val token = tokenService.createToken(userRepository.findAll()[0])
            mockMvc.get("/games") {
                header("Authorization", "Bearer $token")
            }
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
            val token = tokenService.createToken(userRepository.findAll()[0])
            mockMvc.get("/games/$id"){
                header("Authorization", "Bearer $token")
            }
                .andDo { print() }
                .andExpect {
                    status{isOk()}
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.gameId") {value(2)}
                }
        }
        @Test
        fun `should return not found if the game with that id doesn't exist`() {
            val incorrectId = -1L
            val token = tokenService.createToken(userRepository.findAll()[0])
            mockMvc.get("/games/$incorrectId"){
                header("Authorization", "Bearer $token")
            }
                .andDo { print() }
                .andExpect {
                    status { isNotFound() }
                }
        }
        @Test
        fun `should return a saved game`() {
            val token = tokenService.createToken(userRepository.findAll()[0])
            val newGame = Game(steps = 7000)

            val performPost = mockMvc.post("/games") {
                header("Authorization", "Bearer $token")
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
            val givenGameId = gameRepository.findAll()[0].gameId
            val token = tokenService.createToken(userRepository.findAll()[0])
                mockMvc.delete("/games/$givenGameId"){
                    header("Authorization", "Bearer $token")
                }
                    .andDo{print()}
                    .andExpect { status {isOk() }
                mockMvc.get("/games/$givenGameId") {
                    header("Authorization", "Bearer $token")
                }
                    .andExpect{status{isNotFound()}}
            }
        }
}