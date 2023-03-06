package hh.project.discgolf.repositories

import hh.project.discgolf.entities.Course
import hh.project.discgolf.enums.Difficulty
import hh.project.discgolf.repositories.CourseRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.assertj.core.api.Assertions.*
import org.springframework.test.context.ActiveProfiles
import java.util.*

@ExtendWith(value = [SpringExtension::class])
@DataJpaTest
@ActiveProfiles("test")
class CourseRepositoryTests
    @Autowired constructor(
    private val courseRepository : CourseRepository
)

{
    @BeforeEach
    fun init() {
        courseRepository.deleteAll()
        val course1 = Course(courseId = 1L, courseName = "Test1", difficulty = Difficulty.A1)
        val course2 = Course(courseId = 2L, courseName = "Test2", difficulty = Difficulty.A3)
        courseRepository.saveAll(listOf(course1, course2))
    }

    @Test
    fun `should save a course`(){
        val savedCourse = Course(courseId = 3L, courseName = "Test3")
        courseRepository.save(savedCourse)
        assertThat(courseRepository.findByCourseName("Test3")).isPresent
    }

    @Test
    fun `should return all courses`() {
        assertThat(courseRepository.findAll().size).isEqualTo(2)
    }

    @Test
    fun `should return course by name`() {
        val course = courseRepository.findByCourseName("Test1")
        assertThat(course).isPresent
    }

    @Test
    fun `should delete course by id`() {
        for (course in courseRepository.findAll()) {
            courseRepository.deleteById(course.courseId)
            assertThat(courseRepository.findById(course.courseId)).isEmpty
        }
    }

    @Test
    fun `should return list of courses by difficulty`() {
        val difficulty = Difficulty.A1
        val courses = courseRepository.findAllByDifficulty(difficulty)
        assertThat(courses.size).isEqualTo(1)
    }
}