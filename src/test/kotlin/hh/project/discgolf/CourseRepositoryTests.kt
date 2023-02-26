package hh.project.discgolf

import hh.project.discgolf.entities.Course
import hh.project.discgolf.repositories.CourseRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.assertj.core.api.Assertions.*
import org.springframework.test.context.ActiveProfiles

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
        val course1 = Course(courseId = 1L, courseName = "Test1")
        val course2 = Course(courseId = 2L, courseName = "Test2")
        courseRepository.saveAll(listOf(course1, course2))
    }

    @Test
    fun `should save a course`(){
        val savedCourse = Course(courseId = 3L)
        courseRepository.save(savedCourse)
        assertThat(courseRepository.findById(3L)).isNotEmpty
    }

    @Test
    fun `should return all courses`() {
        assertThat(courseRepository.findAll().size).isEqualTo(2)
    }

    @Test
    fun `should return course by id`() {
        assertThat(courseRepository.findById(2L)).isNotEmpty
    }

    @Test
    fun `should delete course by id`() {
        val course = courseRepository.findById(2L).get()
        courseRepository.delete(course)
        assertThat(courseRepository.findById(2L)).isEmpty
    }

    @Test
    fun `should update a course name`() {
        val course = courseRepository.findById(2L).get()
        course.courseName = "new name"
        courseRepository.save(course)
        assertThat(courseRepository.findById(2L).get().courseName).isEqualTo("new name")
    }



}