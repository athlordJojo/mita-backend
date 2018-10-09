package com.jachouni.mitabackend.entities

import java.util.*
import javax.persistence.*

@Entity
data class KindergardenGroup(
        val name: String,
        @ManyToOne
        var kindergarden: Kindergarden
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null

    @OneToMany(mappedBy = "kindergardenGroup")
    val childs: MutableList<Child> = mutableListOf()
}