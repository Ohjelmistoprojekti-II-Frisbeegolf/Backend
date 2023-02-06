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
    var userId: Long = -1,
    @Column(nullable = false, unique = true)
    var username: String = "",
    var email: String = "",
    var password: String = "",
    @Enumerated(EnumType.STRING)
    var role: UserRole? = null
    //TODO: Don't know connections yet. Finish when diagrams are done.
)