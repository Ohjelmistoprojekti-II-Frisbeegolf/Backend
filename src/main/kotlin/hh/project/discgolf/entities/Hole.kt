package hh.project.discgolf.entities

import jakarta.persistence.*
import lombok.Getter
import lombok.Setter
import lombok.ToString

@Entity
class Hole(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    var holeId: Long = -1L,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courseId")
    var course: Course? = null,

    var holePar: Int = 0,

    var holeLength: Int = 0,

    var holeNumber: Int = 0,


    )