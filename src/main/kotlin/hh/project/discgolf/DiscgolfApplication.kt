package hh.project.discgolf

import hh.project.discgolf.utils.FillDB
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Profile

@SpringBootApplication
class DiscgolfApplication {

	@Bean
	@Profile("!test")
	fun insertTestData(fillDB: FillDB) = CommandLineRunner {
		fillDB.fill()
	}

}

fun main(args: Array<String>) {
	runApplication<DiscgolfApplication>(*args)
}
