package hh.project.discgolf

import hh.project.discgolf.entities.Game
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

    @Test
    fun `shouldSaveGame`() {
        val game = Game()
        val savedGame = gameRepository.save(game)
        assertThat(savedGame).usingRecursiveComparison().ignoringFields("gameId").isEqualTo(game)
    }

}