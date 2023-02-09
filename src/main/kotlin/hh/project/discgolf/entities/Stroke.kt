package hh.project.discgolf.entities

import jakarta.persistence.*
import lombok.Getter
import lombok.Setter
import lombok.ToString

@Entity
@Getter
@Setter
@ToString
class Stroke (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    var strokeId: Long = -1L,

    @ManyToOne
    var hole: Hole? = null,

    @ManyToOne
    var game: Game? = null,

    var score: Int = 0,
)