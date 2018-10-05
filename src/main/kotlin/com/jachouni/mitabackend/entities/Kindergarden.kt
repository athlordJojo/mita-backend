package com.jachouni.mitabackend.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import com.jachouni.mitabackend.KindergardenDto
import java.util.*
import javax.persistence.*

@Entity
data class Kindergarden(@Id
                        @GeneratedValue(strategy = GenerationType.AUTO)
                        var id: UUID? = null,
                        val name: String,
                        @ManyToOne
                        @JsonIgnore
                        var customer: Customer? = null) {


    fun toDto(): KindergardenDto {
        return KindergardenDto(
                id = this.id, name = this.name
        )
    }
}

