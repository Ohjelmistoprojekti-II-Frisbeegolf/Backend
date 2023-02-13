package hh.project.discgolf

import hh.project.discgolf.utils.InitializeDB
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Profile

@SpringBootApplication
class DiscgolfApplication {

	@Bean
	@Profile("!test")
	fun insertTestData(initializeDB: InitializeDB) = CommandLineRunner {
		initializeDB.initialize()
	}
}
fun main(args: Array<String>) {
	runApplication<DiscgolfApplication>(*args)
}
