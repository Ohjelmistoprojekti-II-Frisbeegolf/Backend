package hh.project.discgolf.controllers

import hh.project.discgolf.entities.Hole
import hh.project.discgolf.services.HoleService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
class HoleController (private val holeService: HoleService) {

    @GetMapping(value = ["/holes"])
    fun getAllHoles(): MutableIterable<Hole> = holeService.getAllHoles()
}