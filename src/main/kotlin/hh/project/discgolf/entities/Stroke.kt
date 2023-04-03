package hh.project.discgolf.entities

import jakarta.persistence.*

@Entity
class Stroke (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    var strokeId: Long = -1L,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "holeId")
    var hole: Hole? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gameId")
    var game: Game? = null,

    var score: Int = 0,

)