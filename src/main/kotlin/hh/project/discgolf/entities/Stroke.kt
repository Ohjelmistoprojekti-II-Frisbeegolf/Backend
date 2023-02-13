package hh.project.discgolf.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.*
import lombok.Getter
import lombok.Setter
import lombok.ToString

@Entity
class Stroke (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    var strokeId: Long = -1L,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "holeId")
    @JsonIgnoreProperties(value = ["strokes"])
    var hole: Hole? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gameId")
    @JsonIgnore
    var game: Game? = null,

    var score: Int = 0,
)