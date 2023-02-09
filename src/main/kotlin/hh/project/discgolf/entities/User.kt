package hh.project.discgolf.entities

import hh.project.discgolf.enums.UserRole
import jakarta.persistence.*
import lombok.Getter
import lombok.Setter
import lombok.ToString

@Entity(name = "users")
@Getter
@Setter
@ToString
class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    var userId: Long = -1,

    @OneToMany(mappedBy = "gameId")
    var games: List<Game> = emptyList(),

    @Column(nullable = false, unique = true)
    var username: String = "",

    @Column(unique = true)
    var email: String = "",

    @Column(nullable = false)
    var password: String = "",

    @Enumerated(EnumType.STRING)
    var role: UserRole? = null,


)