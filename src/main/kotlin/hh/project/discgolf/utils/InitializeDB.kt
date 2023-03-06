package hh.project.discgolf.utils

import hh.project.discgolf.entities.*
import hh.project.discgolf.enums.Difficulty
import hh.project.discgolf.enums.UserRole
import hh.project.discgolf.repositories.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class InitializeDB @Autowired constructor(
    private val courseRepo : CourseRepository,
    private val gameRepo : GameRepository,
    private val holeRepo : HoleRepository,
    private val strokeRepo : StrokeRepository,
    private val userRepo : UserRepository

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
                email = "keijonen@gmail.com",
                password = "enolekeijo",
                role = UserRole.USER
            )
        )

        userRepo.save(
            User(
                username = "Maija",
                email = "maijakas@hotmail.com",
                password = "olenmaija",
                role = UserRole.USER
            )
        )

        userRepo.save(
            User(
                username = "admin",
                email = "admin@discgolf.com",
                password = "admin",
                role = UserRole.ADMIN
            )
        )
    }

    private fun createTestCoursesToDB() {
        val puolarmaari = courseRepo.save(
            Course(
                courseName = "Puolarmaari",
                courseStreetaddress = "Puolarmaari 3",
                coursePostalcode = "02210",
                courseTown = "Espoo",
                difficulty = Difficulty.A1,
                latitude = 60.17669136116457,
                longitude = 24.698378641732024
            )
        )

        createHolesForCourse(course = puolarmaari, holeAmount = 20)

        val oittaaKalliometsa = courseRepo.save(
            Course(
                courseName = "Oittaa Kalliometsä",
                courseStreetaddress = "Oittaantie",
                coursePostalcode = "02740",
                courseTown = "Espoo",
                difficulty = Difficulty.A1,
                latitude = 60.240064261888136,
                longitude = 24.665466041735332
            )
        )

        createHolesForCourse(course = oittaaKalliometsa, holeAmount = 18)

        val tali = courseRepo.save(
            Course(
                courseName = "Talin frisbeegolfpuisto",
                courseStreetaddress = "Takkatie 36",
                coursePostalcode = "00370",
                courseTown = "Helsinki",
                difficulty = Difficulty.AA1,
                latitude = 60.21311666971948,
                longitude = 24.847708022685616
            )
        )

        createHolesForCourse(course = tali, holeAmount = 18)
    }

    private fun createHolesForCourse(course : Course, holeAmount: Int) {
        for (hole in 1..holeAmount) {
            holeRepo.save(
                Hole(course = course, holeLength = (30..120).random(), holeNumber = hole, holePar = (2..5).random())
            )
        }
    }

    private fun createTestGamesToDB() {
        val gameAtPuolarmaari = gameRepo.save(
            Game(
                user = userRepo.findByUsername(username = "Keijo"),
                course = courseRepo.findByCourseName(courseName = "Puolarmaari").get(),
                steps = 5000,
                startingDatetime = LocalDateTime.now(),
                endingDatetime = LocalDateTime.now().plusMinutes(90)
            )
        )

        createStrokesForGame(game = gameAtPuolarmaari)

        val gameAtOittaaKalliometsa = gameRepo.save(
            Game(
                user = userRepo.findByUsername(username = "Maija"),
                course = courseRepo.findByCourseName(courseName = "Oittaa Kalliometsä").get(),
                steps = 8000,
                startingDatetime = LocalDateTime.now(),
                endingDatetime = LocalDateTime.now().plusMinutes(120)
            )
        )

        createStrokesForGame(game = gameAtOittaaKalliometsa)

        val gameAtTali = gameRepo.save(
            Game(
                user = userRepo.findByUsername(username = "Keijo"),
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
