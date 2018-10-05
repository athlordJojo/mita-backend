package com.jachouni.mitabackend.entities

import java.util.*
import javax.persistence.*

@Entity
data class Kindergarden(@Id
                        @GeneratedValue(strategy = GenerationType.AUTO)
                        var id: UUID? = null,
                        val name: String,
                        @ManyToOne
                        var customer: Customer? = null)