package com.jachouni.mitabackend.entities

import java.util.*
import javax.persistence.*

@Entity
data class Child(val firstname: String,
                 val lastname: String,
                 @ManyToOne
                 var kindergardenGroup: KindergardenGroup? = null
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null

    @OneToMany
    val dayEntries: List<DayEntry> = mutableListOf()
}