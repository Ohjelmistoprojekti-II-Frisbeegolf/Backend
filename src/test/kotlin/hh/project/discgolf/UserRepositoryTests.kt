package hh.project.discgolf

import org.assertj.core.api.Assertions.*
import hh.project.discgolf.entities.User
import hh.project.discgolf.repositories.UserRepository
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
class UserRepositoryTests @Autowired constructor(
    val userRepository: UserRepository
) {

    @BeforeEach
    fun init() {
        userRepository.deleteAll()
        val user1 = User(userId = 1L, username = "Matti", email = "matti@mail.com", password = "df68327ghj")
        val user2 = User(userId = 2L, username = "Maija", email = "maija@mail.com", password = "jdncs788ds")
        userRepository.saveAll(listOf(user1, user2))
    }

    @Test
    fun `should save a user`() {
        val newUser = User(userId = 3L, username = "Ville", email = "v@mail.com", password = "hjasasjf23")
        userRepository.save(newUser)
        assertThat(userRepository.findById(3L)).isNotNull
    }

    @Test
    fun `should return all users`() {
        val users = userRepository.findAll()
        assertThat(users).isNotEmpty
        assertThat(users).hasSize(2)
    }

    @Test
    fun `should return user by given username`() {
        val user = userRepository.findByUsername("Matti")
        assertThat(user).isNotNull
    }

    @Test
    fun `should return user by given id`() {
        for (id in 1..userRepository.findAll().size) {
            assertThat(userRepository.findById(id.toLong())).isNotNull
        }
    }

    @Test
    fun `should delete user by given id`() {
        for (user in userRepository.findAll()) {
            userRepository.deleteById(user.userId)
            assertThat(userRepository.findById(user.userId)).isEmpty
        }
    }
}