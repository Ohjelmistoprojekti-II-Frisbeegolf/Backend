package hh.project.discgolf.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import lombok.Getter
import lombok.Setter
import lombok.ToString

@Entity
@Getter
@Setter
@ToString
class Hole(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    var holeId: Long = -1L,

    @ManyToOne
    var course: Course? = null,

    var holePar: Number = 0,

    var holeLength: Number = 0,

    var holeNumber: Number = 0,


)