package hh.project.discgolf.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import hh.project.discgolf.enums.UserRole
import jakarta.persistence.*

@Entity(name = "users")
class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    var userId: Long = -1,

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @JsonIgnore
    var games: List<Game> = emptyList(),

    @Column(nullable = false, unique = true)
    var username: String? = null,

    @Column(unique = true)
    var email: String = "",

    @Column(nullable = false)
    var password: String? = null,

    @Enumerated(EnumType.STRING)
    var role: UserRole? = null,

    )