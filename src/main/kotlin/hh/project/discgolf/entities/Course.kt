package hh.project.discgolf.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import hh.project.discgolf.enums.CourseDifficulty
import jakarta.persistence.*

@Entity
class Course(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    var courseId: Long = -1L,

    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
    @JsonIgnore
    var games: List<Game> = emptyList(),

    @Column(unique = true)
    var courseName: String = "",

    var courseStreetaddress: String = "",

    var courseTown: String = "",

    var coursePostalcode: String = "",

    @Enumerated(EnumType.STRING)
    var courseDifficulty: CourseDifficulty? = null,

    @Column(nullable = false)
    var latitude: Double = 0.0,

    @Column(nullable = false)
    var longitude: Double = 0.0,

    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
    var holes: List<Hole> = emptyList()
    )
