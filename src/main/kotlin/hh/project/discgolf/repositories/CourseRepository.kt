package hh.project.discgolf.repositories

import hh.project.discgolf.entities.Course
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface CourseRepository : JpaRepository<Course, Long> {

    fun findByCourseName(courseName: String) : Optional<Course>

}