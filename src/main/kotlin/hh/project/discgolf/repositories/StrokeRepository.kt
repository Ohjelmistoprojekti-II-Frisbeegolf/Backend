package hh.project.discgolf.repositories

import hh.project.discgolf.entities.Stroke
import org.springframework.data.repository.CrudRepository

interface StrokeRepository : CrudRepository<Stroke, Long> {
}