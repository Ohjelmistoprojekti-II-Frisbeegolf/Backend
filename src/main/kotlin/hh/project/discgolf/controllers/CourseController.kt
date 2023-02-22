package hh.project.discgolf.controllers

import hh.project.discgolf.entities.Course
import hh.project.discgolf.services.CourseService
import org.springframework.boot.SpringApplication
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
class CourseController(private val courseService : CourseService) {

    @ExceptionHandler(NoSuchElementException::class)
    fun handleElementNotFound(e: NoSuchElementException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @GetMapping(value = ["/courses"])
    fun getAllCourses(): List<Course> = courseService.getAllCourses()

    @GetMapping(value = ["/courses/{id}"])
    fun getCourseById(@PathVariable("id") id : Long) : Course = courseService.getCourseById(id)

}