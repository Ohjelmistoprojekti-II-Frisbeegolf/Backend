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
        assertThat(courseRepository.findById(3L)).isNotNull
    }

    @Test
    fun `should return all courses`() {
        assertThat(courseRepository.findAll().size).isEqualTo(100)
    }

    @Test
    fun `should return course by id`() {
        for (id in 1..courseRepository.findAll().size) {
            assertThat(courseRepository.findById(id.toLong())).isNotNull
        }
    }

    @Test
    fun `should delete course by id`() {
        for (course in courseRepository.findAll()) {
            var courseId : Long = course.courseId
            courseRepository.deleteById(courseId)
            assertThat(courseRepository.findById(courseId)).isEmpty
        }
    }

    @Test
    fun `should update a course name`() {
        val courseId = 1L
        val course = courseRepository.findById(courseId).get()
        course.courseName = "new name"
        assertThat(courseRepository.findById(courseId).get().courseName).isEqualTo("new name")
    }



}