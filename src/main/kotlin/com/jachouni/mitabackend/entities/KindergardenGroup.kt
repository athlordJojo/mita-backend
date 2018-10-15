package com.jachouni.mitabackend.entities

import java.util.*
import javax.persistence.*
import javax.persistence.FetchType


@Entity
@NamedQueries(value = [NamedQuery(name = "KindergardenGroup.findByCustomerIdAndKindergardenId", query = "Select kg from KindergardenGroup ")]


)
@Table(name = "kindergardengroup")
data class KindergardenGroup(
        @Column(nullable = false)
        var name: String,
        @ManyToOne(optional = false)
        var kindergarden: Kindergarden,
        @OneToOne(mappedBy = "group", cascade = [CascadeType.ALL],
                fetch = FetchType.LAZY)
        val groupbook: Groupbook
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null

    @OneToMany(mappedBy = "kindergardenGroup", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    val childs: MutableList<Child> = mutableListOf()

    fun addChild(child: Child) {
        childs.add(child)
    }

    fun removeChild(child: Child) {
        childs.remove(child)
    }

}