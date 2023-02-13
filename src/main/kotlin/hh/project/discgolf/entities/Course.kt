package hh.project.discgolf.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import lombok.Getter
import lombok.Setter
import lombok.ToString

@Entity
class Course(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    var courseId: Long = -1L,

    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
    var holes: List<Hole> = emptyList(),

    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
    @JsonIgnore
    var games: List<Game> = emptyList(),

    @Column(unique = true)
    var courseName: String = "",

    var courseStreetaddress: String = "",

    var courseTown: String = "",

    var coursePostalcode: String = "",




    )
