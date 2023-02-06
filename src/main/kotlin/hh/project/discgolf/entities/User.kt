package hh.project.discgolf.entities

import hh.project.discgolf.enums.UserRole
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var userId: Long = -1,
    @Column(nullable = false, unique = true)
    var username: String = "",
    var email: String = "",
    var password: String = "",
    var role: UserRole? = null
)