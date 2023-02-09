package hh.project.discgolf.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import lombok.Getter
import lombok.Setter
import lombok.ToString

@Entity
class Hole(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    var holeId: Long = -1L,

    @ManyToOne
    @JoinColumn(name = "courseId")
    var course: Course? = null,

    var holePar: Int = 0,

    var holeLength: Int = 0,

    var holeNumber: Int = 0,


    )