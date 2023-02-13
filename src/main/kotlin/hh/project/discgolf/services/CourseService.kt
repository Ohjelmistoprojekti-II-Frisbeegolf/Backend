package hh.project.discgolf.services

import hh.project.discgolf.entities.Course
import hh.project.discgolf.repositories.CourseRepository
import org.springframework.stereotype.Service

@Service
class CourseService(private val courseRepository: CourseRepository) {

    fun getAllCourses(): Iterable<Course> = courseRepository.findAll()

}