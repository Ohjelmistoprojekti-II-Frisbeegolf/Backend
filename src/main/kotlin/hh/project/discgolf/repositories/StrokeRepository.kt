package hh.project.discgolf.repositories

import hh.project.discgolf.entities.Stroke
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository

interface StrokeRepository : JpaRepository<Stroke, Long> {
}