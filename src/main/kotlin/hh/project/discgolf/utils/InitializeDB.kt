package hh.project.discgolf.utils

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import hh.project.discgolf.entities.*
import hh.project.discgolf.enums.UserRole
import hh.project.discgolf.repositories.*
import hh.project.discgolf.services.HashService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.io.File
import java.time.LocalDateTime

@Component
class InitializeDB @Autowired constructor(
    private val courseRepo : CourseRepository,
    private val gameRepo : GameRepository,
    private val holeRepo : HoleRepository,
    private val strokeRepo : StrokeRepository,
    private val userRepo : UserRepository,
    private val hashService: HashService
) {

    fun initialize() {
        deleteEverythingFromDB()
        createTestUsersToDB()
        createTestCoursesToDB()
        createTestGamesToDB()
    }

    private fun deleteEverythingFromDB() {
        courseRepo.deleteAll()
        gameRepo.deleteAll()
        holeRepo.deleteAll()
        strokeRepo.deleteAll()
        userRepo.deleteAll()
    }

    private fun createTestUsersToDB() {
        userRepo.save(
            User(
                username = "Keijo",
                password = hashService.hashBcrypt("salasana"),
                role = UserRole.USER
            )
        )

        userRepo.save(
            User(
                username = "Maija",
                password = hashService.hashBcrypt("salasana"),
                role = UserRole.USER
            )
        )

        userRepo.save(
            User(
                username = "admin",
                password = hashService.hashBcrypt("admin"),
                role = UserRole.ADMIN
            )
        )
    }

    private fun createTestCoursesToDB() {
        val jsonTextList = parseJsonFileToCourseList()
        for (course in jsonTextList) {
            val newCourse = Course(
                courseName = course.courseName,
                courseStreetaddress = course.courseStreetaddress,
                courseTown = course.courseTown,
                coursePostalcode = course.coursePostalcode,
                courseDifficulty = course.courseDifficulty,
                latitude = course.latitude,
                longitude = course.longitude,
            )
            val savedCourse = courseRepo.save(newCourse)
            createHolesForCourse(savedCourse, course.holes)
        }
    }

    private fun parseJsonFileToCourseList(): List<Course> {
        val mapper = jacksonObjectMapper()

        val jsonString: String = File("./src/main/resources/courses.json").readText(Charsets.UTF_8)
        return mapper.readValue<List<Course>>(jsonString)
    }

    private fun createHolesForCourse(course : Course, holes: List<Hole>) {
        for (hole: Hole in holes) {
            holeRepo.save(
                Hole(course = course, holeLength = hole.holeLength, holeNumber = hole.holeNumber, holePar = hole.holePar)
            )
        }
    }

    private fun createTestGamesToDB() {
        val gameAtPuolarmaari = gameRepo.save(
            Game(
                user = userRepo.findByUsername(username = "Keijo").get(),
                course = courseRepo.findByCourseName(courseName = "Puolarmaari frisbeegolf").get(),
                steps = 5000,
                startingDatetime = LocalDateTime.now(),
                endingDatetime = LocalDateTime.now().plusMinutes(90)
            )
        )

        createStrokesForGame(game = gameAtPuolarmaari)


         val gameAtOittaaKalliometsa = gameRepo.save(
            Game(
                user = userRepo.findByUsername(username = "Maija").get(),
                course = courseRepo.findByCourseName(courseName = "Oittaa Kalliometsä").get(),
                steps = 8000,
                startingDatetime = LocalDateTime.now(),
                endingDatetime = LocalDateTime.now().plusMinutes(120)
            )
        )

        createStrokesForGame(game = gameAtOittaaKalliometsa)

        val gameAtTali = gameRepo.save(
            Game(
                user = userRepo.findByUsername(username = "Keijo").get(),
                course = courseRepo.findByCourseName(courseName = "Talin frisbeegolfpuisto").get(),
                steps = 4500,
                startingDatetime = LocalDateTime.now(),
                endingDatetime = LocalDateTime.now().plusMinutes(200)
            )
        )

        createStrokesForGame(game = gameAtTali)
    }

    private fun createStrokesForGame(game: Game){
        for (hole: Hole in game.course!!.holes) {
            strokeRepo.save(
                Stroke(hole = hole, game = game, score = (1..5).random())
            )
        }
    }


}
