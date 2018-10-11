package com.jachouni.mitabackend.entities

import java.util.*
import javax.persistence.*
import javax.persistence.FetchType


@Entity
data class KindergardenGroup(
        @Column(nullable = false)
        val name: String,
        @ManyToOne(optional = false)
        var kindergarden: Kindergarden,
        @OneToOne(mappedBy = "group", cascade = [CascadeType.ALL],
                fetch = FetchType.LAZY)
        val groupbook: Groupbook
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null

    @OneToMany(mappedBy = "kindergardenGroup", orphanRemoval = true, fetch = FetchType.LAZY)
    val childs: MutableList<Child> = mutableListOf()

    fun addChild(child: Child) {
        childs.add(child)
    }

    fun removeChild(child: Child) {
        childs.remove(child)
    }

}