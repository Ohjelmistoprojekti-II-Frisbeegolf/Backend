package hh.project.discgolf.controllers

import hh.project.discgolf.entities.Course
import hh.project.discgolf.services.CourseService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
class CourseController(private  val courseService : CourseService) {

    @GetMapping(value = ["/courses"])
    fun getAllCourses(): List<Course> = courseService.getAllCourses()

}