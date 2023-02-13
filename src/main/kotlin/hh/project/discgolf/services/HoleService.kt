package hh.project.discgolf.services

import hh.project.discgolf.entities.Hole
import hh.project.discgolf.repositories.HoleRepository
import org.springframework.stereotype.Service

@Service
class HoleService (private val holeRepository: HoleRepository){

    fun getAllHoles(): List<Hole> = holeRepository.findAll()
}