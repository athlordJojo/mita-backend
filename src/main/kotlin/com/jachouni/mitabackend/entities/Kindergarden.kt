package com.jachouni.mitabackend.entities

import java.util.*
import javax.persistence.*

@Entity
data class Kindergarden(
        @Column(nullable = false)
        var name: String,
        @ManyToOne(optional = false)
        var customer: Customer
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null

    @OneToMany(mappedBy = "kindergarden", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    val kindergardenGroups: MutableList<KindergardenGroup> = mutableListOf()

    fun addKindergardenGroup(kindergardenGroup: KindergardenGroup) {
        kindergardenGroups.add(kindergardenGroup)
    }

    fun removeKindergardenGroup(kindergardenGroup: KindergardenGroup) {
        kindergardenGroups.remove(kindergardenGroup)
    }
}

