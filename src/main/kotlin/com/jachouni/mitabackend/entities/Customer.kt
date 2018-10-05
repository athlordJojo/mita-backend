package com.jachouni.mitabackend.entities

import java.util.*
import javax.persistence.*

@Entity
data class Customer(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: UUID? = null,
        val name: String) {

    @OneToMany(mappedBy = "customer")
    val kindergardens: MutableList<Kindergarden> = mutableListOf()

}