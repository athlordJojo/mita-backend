package com.jachouni.mitabackend.entities

import java.util.*
import javax.persistence.*

@Entity
data class Kindergarden(
        val name: String,
        @ManyToOne(optional = false)
        var customer: Customer
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null

    @OneToOne(mappedBy = "group", cascade = [CascadeType.ALL],
            fetch = FetchType.LAZY, optional = false)
    val groupbook: Groupbook? = null


    @OneToMany(mappedBy = "kindergarden", cascade = [CascadeType.ALL], orphanRemoval = true)
    val kindergardenGroups: MutableList<KindergardenGroup> = mutableListOf()
}

