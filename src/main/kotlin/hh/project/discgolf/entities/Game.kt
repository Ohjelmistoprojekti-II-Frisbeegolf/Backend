package hh.project.discgolf.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import lombok.Getter
import lombok.Setter
import lombok.ToString
import java.time.LocalDateTime

@Entity
@Getter
@Setter
@ToString
class Game(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    var gameId: Long = -1L,

    @ManyToOne
    var user: User? = null,

    var strokes: Number = -1,

    var steps: Number = 0,

    var score: Number = 0,

    var date: LocalDateTime? = null


)