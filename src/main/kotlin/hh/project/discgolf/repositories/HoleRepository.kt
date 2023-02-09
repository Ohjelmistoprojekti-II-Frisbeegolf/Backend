package hh.project.discgolf.repositories

import hh.project.discgolf.entities.Hole
import org.springframework.data.repository.CrudRepository

interface HoleRepository : CrudRepository<Hole, Long> {
}