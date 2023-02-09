package hh.project.discgolf.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
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

    @ManyToOne
    var course: Course? = null,

    @OneToMany(mappedBy = "strokeId")
    var strokes: List<Stroke> = emptyList(),

    var steps: Number = 0,

    var startingDatetime: LocalDateTime? = null,

    var endingDatetime: LocalDateTime? = null,
)