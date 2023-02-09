package hh.project.discgolf

import hh.project.discgolf.utils.FillDB
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class DiscgolfApplication {
	@Bean
	fun insertTestData(fillDB: FillDB) = CommandLineRunner {
		fillDB.fill()
	}

}

fun main(args: Array<String>) {
	runApplication<DiscgolfApplication>(*args)
}
