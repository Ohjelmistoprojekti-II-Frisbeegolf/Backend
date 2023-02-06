package hh.project.discgolf.entities

import jakarta.persistence.Column
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne

class Course(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    var courseId: Long = -1L,

    var courseName: String = "",

    var courseAddress: String = "",

    var courseTown: String = "",

    var coursePostalcode: String = "",

    //TODO: ManyToMany connection.
    @ManyToMany
    var holers: Collection<Hole>
)