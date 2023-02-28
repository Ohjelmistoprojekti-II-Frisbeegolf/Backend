package hh.project.discgolf.services

import hh.project.discgolf.entities.Course
import hh.project.discgolf.repositories.CourseRepository
import org.springframework.stereotype.Service
import java.lang.NullPointerException

@Service
class CourseService(private val courseRepository: CourseRepository) {

    fun getAllCourses(): List<Course> = courseRepository.findAll()

    fun getCourseById(courseId: Long): Course =
        courseRepository.findById(courseId)
            .orElseThrow {NoSuchElementException("Could not find course with given id")}

    fun getCourseByCourseName(courseName: String): Course =
        courseRepository.findByCourseName(courseName.replace("_", " "))
            .orElseThrow {NoSuchElementException("Could not find course with given name")}

    fun createNewCourse(course: Course): Course =
        courseRepository.save(course)
}