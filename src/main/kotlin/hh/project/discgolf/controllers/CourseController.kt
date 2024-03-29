package hh.project.discgolf.controllers

import hh.project.discgolf.entities.Course
import hh.project.discgolf.enums.CourseDifficulty
import hh.project.discgolf.services.CourseService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
class CourseController(private val courseService: CourseService) {

    @GetMapping(value = ["/courses"])
    fun getAllCourses(): List<Course> = courseService.getAllCourses()

    @GetMapping(value = ["/courses/{id}"])
    fun getCourseById(@PathVariable("id") id : Long): Course = courseService.getCourseById(id)

    @GetMapping(value = ["/courses/name/{name}"])
    fun getCourseByName(@PathVariable("name") courseName: String): Course =
        courseService.getCourseByCourseName(courseName)

    @GetMapping(value = ["/courses/difficulty/{diff}"])
    fun getCoursesByDifficulty(@PathVariable("diff") courseDifficulty: CourseDifficulty): List<Course> =
        courseService.getCoursesByDifficulty(courseDifficulty)

    @PostMapping(value = ["/courses"])
    @PreAuthorize("hasRole('ADMIN')")
    fun createNewCourse(@RequestBody payload: Course): Course =
        courseService.createNewCourse(payload)
}