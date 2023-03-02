package hh.project.discgolf.controllers

import hh.project.discgolf.entities.Stroke
import hh.project.discgolf.services.StrokeService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
class StrokeController (private val strokeService: StrokeService) {

    @GetMapping(value = ["/strokes"])
    fun getAllStrokes(): List<Stroke> = strokeService.getAllStrokes()

    @GetMapping(value = ["/strokes/{id}"])
    fun getSingleStroke(@PathVariable("id") strokeId: Long): Stroke =
        strokeService.getSingleStrokeById(strokeId)

    @PostMapping(value = ["/strokes"])
    @ResponseStatus(HttpStatus.CREATED)
    fun createStroke(@RequestBody payload: Stroke): Stroke =
        strokeService.createNewStroke(payload)
}