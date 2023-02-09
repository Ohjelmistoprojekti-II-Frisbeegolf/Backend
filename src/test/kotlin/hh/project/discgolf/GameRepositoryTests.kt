package hh.project.discgolf

import hh.project.discgolf.entities.Game
import hh.project.discgolf.entities.User
import hh.project.discgolf.repositories.GameRepository
import hh.project.discgolf.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.*

@DataJpaTest
class GameRepositoryTests @Autowired constructor(
    val gameRepository: GameRepository,
    val userRepository: UserRepository
) {

    private val testUser = User()
    private val testGame = Game(1, testUser)
    @Test
    fun `shouldSaveGame`() {
        val savedGame = gameRepository.save(testGame)
        assertThat(savedGame).usingRecursiveComparison().ignoringFields("gameId").isEqualTo(testGame)
    }

    @Test
    fun `shouldReturnGameById`(){
        val game = gameRepository.findById(1)
        assertThat(game).isPresent
    }

    @Test
    fun `shouldReturnGameByUser`() {
        userRepository.save(testUser)
        gameRepository.save(testGame)
        val game = gameRepository.findByUser(testUser)
        assertThat(game).isNotNull
    }


}