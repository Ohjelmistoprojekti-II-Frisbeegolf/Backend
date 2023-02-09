package hh.project.discgolf.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import lombok.Getter
import lombok.Setter
import lombok.ToString

@Entity
@Getter
@Setter
@ToString
class Course(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    var courseId: Long = -1L,

    @OneToMany(mappedBy = "holeId")
    var holes: List<Hole> = emptyList(),

    @OneToMany(mappedBy = "gameId")
    var games: List<Game> = emptyList(),

    @Column(unique = true)
    var courseName: String = "",

    var courseStreetaddress: String = "",

    var courseTown: String = "",

    var coursePostalcode: String = "",


)