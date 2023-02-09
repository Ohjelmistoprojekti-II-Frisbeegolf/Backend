package hh.project.discgolf

import hh.project.discgolf.entities.Game
import hh.project.discgolf.entities.User
import hh.project.discgolf.enums.UserRole
import hh.project.discgolf.repositories.GameRepository
import hh.project.discgolf.repositories.UserRepository
import org.aspectj.lang.annotation.After
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Profile
import org.springframework.test.context.ActiveProfiles

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach

@DataJpaTest

class GameRepositoryTests @Autowired constructor(
    val gameRepository: GameRepository,
    val userRepository: UserRepository
) {

    @BeforeAll
    // save test user and test game
    fun setup() {
        val testUser = User(1, emptyList(), "John", "john@test.com")
        userRepository.save(testUser)
        gameRepository.save(Game(1, testUser, null, emptyList(), 2000, null, null))
    }


    @Test
    fun `shouldReturnGameById`() {
        val game = gameRepository.findById(1)
        assertThat(game).isNotEmpty
    }

    @Test
    fun `shouldReturnAllGames`() {
        val games = gameRepository.findAll()
        assertThat(games).isNotEmpty
    }
    /*
    @AfterEach
    @Test
    fun `shouldSaveGame`() {
        val game = Game()
        val savedGame = gameRepository.save(game)
        assertThat(savedGame).usingRecursiveComparison().ignoringFields("gameId").isEqualTo(game)
    }*/

}