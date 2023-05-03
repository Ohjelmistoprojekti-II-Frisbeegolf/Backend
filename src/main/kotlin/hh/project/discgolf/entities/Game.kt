package hh.project.discgolf.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class Game(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    var gameId: Long = -1L,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    var user: User? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courseId")
    var course: Course? = null,

    @OneToMany(mappedBy = "game", fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    var strokes: List<Stroke> = emptyList(),

    var steps: Int = 0,

    var startingDatetime: LocalDateTime? = null,

    var endingDatetime: LocalDateTime? = null,

)