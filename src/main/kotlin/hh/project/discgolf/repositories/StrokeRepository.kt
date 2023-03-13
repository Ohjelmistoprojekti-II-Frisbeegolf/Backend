package hh.project.discgolf.repositories

import hh.project.discgolf.entities.Stroke
import org.springframework.data.jpa.repository.JpaRepository

interface StrokeRepository : JpaRepository<Stroke, Long> {
}