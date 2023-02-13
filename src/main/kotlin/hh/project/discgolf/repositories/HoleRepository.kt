package hh.project.discgolf.repositories

import hh.project.discgolf.entities.Hole
import org.springframework.data.jpa.repository.JpaRepository

interface HoleRepository : JpaRepository<Hole, Long> {
}