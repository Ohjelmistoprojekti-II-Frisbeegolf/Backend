package hh.project.discgolf.repositories

import hh.project.discgolf.entities.Course
import org.springframework.data.jpa.repository.JpaRepository

interface CourseRepository : JpaRepository<Course, Long> {

}