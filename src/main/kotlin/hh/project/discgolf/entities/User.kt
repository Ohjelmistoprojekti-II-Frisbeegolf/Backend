package hh.project.discgolf.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import hh.project.discgolf.enums.ScoringSystem
import hh.project.discgolf.enums.UserRole
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

@Entity(name = "users")
class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    var userId: Long = -1,

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = [CascadeType.REFRESH])
    @JsonIgnore
    var games: List<Game> = emptyList(),

    @Column(nullable = false, unique = true)
    @NotBlank(message = "username is mandatory")
    @Size(min = 4, max = 32)
    var username: String? = null,

    @Column(unique = true)
    var email: String = "",

    @Column(nullable = false)
    @NotBlank(message = "password is mandatory")
    @Size(min = 8, max = 32)
    var password: String? = null,

    @Enumerated(EnumType.STRING)
    @NotEmpty
    var role: UserRole? = null,

    @Transient
    var gamesPlayed:  Int = 0,

    @Transient
    var totalTimePlayed: String = "00:00:00",

    @Transient
    var totalThrowsThrown : Int = 0,

    @Transient
    var totalSteps : Int? = 0,

    @Transient
    var results : LinkedHashMap<ScoringSystem, Int> = LinkedHashMap()

    )