package com.jachouni.mitabackend.entities

import com.jachouni.mitabackend.CustomerDto
import java.util.*
import javax.persistence.*

@Entity
data class Customer(
        val name: String) {

    @OneToMany(mappedBy = "customer", orphanRemoval = true, fetch = FetchType.LAZY)
    val kindergardens: MutableList<Kindergarden> = mutableListOf()

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null

    fun toDto(): CustomerDto? {
        return CustomerDto(id = this.id, name = this.name)
    }

}