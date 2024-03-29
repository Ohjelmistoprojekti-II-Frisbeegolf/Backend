package hh.project.discgolf.repositories

import hh.project.discgolf.entities.Game
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(value = [SpringExtension::class])
@DataJpaTest
@ActiveProfiles("test")
class GameRepositoryTests
        @Autowired constructor(
        val gameRepository: GameRepository
    )

{
    @BeforeEach
    fun init() {
        gameRepository.deleteAll()
        val game1 = Game()
        val game2 = Game()
        gameRepository.saveAll(listOf(game1, game2))
    }

    @Test
    fun `should save a game`() {
        val savedGame = gameRepository.save(Game())
        assertThat(savedGame).isNotNull
    }

    @Test
    fun `should return all games`() {
        val games = gameRepository.findAll()
        assertThat(games).hasSize(2)
    }

    @Test
    fun `should return a game by id`() {
        for (id in 1..gameRepository.findAll().count()) {
            val game = gameRepository.findById(id.toLong())
            assertThat(game).isNotNull
        }
    }

    @Test
    fun `should delete games by id`() {
        for (game in gameRepository.findAll()) {
            gameRepository.deleteById(game.gameId)
            assertThat(gameRepository.findById(game.gameId)).isEmpty
        }
    }
}