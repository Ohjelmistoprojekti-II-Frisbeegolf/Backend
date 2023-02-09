package hh.project.discgolf.repositories

import hh.project.discgolf.entities.Course
import org.springframework.data.repository.CrudRepository

interface CourseRepository : CrudRepository<Course, Long> {
}