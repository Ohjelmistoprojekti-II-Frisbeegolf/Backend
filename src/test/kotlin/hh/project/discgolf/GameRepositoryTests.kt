package hh.project.discgolf

import hh.project.discgolf.entities.Game
import hh.project.discgolf.entities.User
import hh.project.discgolf.repositories.GameRepository
import hh.project.discgolf.repositories.UserRepository
import hh.project.discgolf.utils.FillDB
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.*
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean

@DataJpaTest
class GameRepositoryTests @Autowired constructor(
    val gameRepository: GameRepository,
) {

    @Test
    fun `Should save game `() {
        val game = Game()
        val savedGame = gameRepository.save(game)
        assertThat(game).usingRecursiveComparison().ignoringFields("gameId").isEqualTo(savedGame)
    }

}