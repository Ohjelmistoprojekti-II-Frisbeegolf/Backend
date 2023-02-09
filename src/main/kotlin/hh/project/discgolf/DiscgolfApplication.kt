package hh.project.discgolf

import hh.project.discgolf.entities.*
import hh.project.discgolf.enums.UserRole
import hh.project.discgolf.repositories.*
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.time.LocalDateTime

@SpringBootApplication
class DiscgolfApplication {
	@Bean
	fun fillDB(courseRepo : CourseRepository, gameRepo : GameRepository,
			   holeRepo : HoleRepository, strokeRepo : StrokeRepository, userRepo : UserRepository) = CommandLineRunner {

		// Users for testdata
		val keijo = userRepo.save(User(username = "Keijo", email = "keijonen@gmail.com", password = "enolekeijo", role = UserRole.USER))
		val maija = userRepo.save(User(username = "Maija", email = "maijakas@hotmail.com", password = "olenmaija", role = UserRole.USER))
		val admin = userRepo.save(User(username = "admin", email = "admin@discgolf.com", password = "admin", role = UserRole.ADMIN))

		// Courses for testdata
		val puolarmaari = courseRepo.save(Course(courseName = "Puolarmaari", courseStreetaddress = "Puolarmaari 3", coursePostalcode = "02210", courseTown = "Espoo"))
		val oittaaKalliometsa = courseRepo.save(Course(courseName = "Oittaa Kalliometsä", courseStreetaddress = "Oittaantie", coursePostalcode = "02740", courseTown = "Espoo"))
		val tali = courseRepo.save(Course(courseName = "Talin frisbeegolfpuisto", courseStreetaddress = "Takkatie 36", coursePostalcode = "00370", courseTown = "Helsinki"))

		// Holes for Puolarmaari
		for (hole in 1..20) {
			holeRepo.save(Hole(course = puolarmaari, holeLength = 50, holeNumber = hole, holePar = 3))
		}

		// Holes for Oittaan Kalliometsä
		for (hole in 1..18) {
			holeRepo.save(Hole(course = oittaaKalliometsa, holeLength = 75, holeNumber = hole, holePar = 3))
		}

		// Holes for Tali
		for (hole in 1..18) {
			holeRepo.save(Hole(course = tali, holeLength = 90, holeNumber = hole, holePar = 5))
		}

		val gameAtPuolarmaari = gameRepo.save(Game(user = keijo, course = puolarmaari, steps = 5000, startingDatetime = LocalDateTime.now(),
			endingDatetime = LocalDateTime.now().plusMinutes(90)))

		val gameAtOittaaKalliometsa = gameRepo.save(Game(user = maija, course = oittaaKalliometsa, steps = 8000, startingDatetime = LocalDateTime.now(),
			endingDatetime = LocalDateTime.now().plusMinutes(120)))

		val gameAtTali = gameRepo.save(Game(user = keijo, course = tali, steps = 4500, startingDatetime = LocalDateTime.now(),
			endingDatetime = LocalDateTime.now().plusMinutes(200)))

		println(puolarmaari.toString())
	}

}

fun main(args: Array<String>) {
	runApplication<DiscgolfApplication>(*args)
}
