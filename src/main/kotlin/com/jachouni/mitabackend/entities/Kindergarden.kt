package com.jachouni.mitabackend.entities

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Kindergarden(@Id
                        @GeneratedValue(strategy = GenerationType.AUTO)
                        var id: UUID?,
                        val name: String) {
}