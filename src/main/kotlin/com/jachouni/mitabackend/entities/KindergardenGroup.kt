package com.jachouni.mitabackend.entities

import java.util.*
import javax.persistence.*
import javax.persistence.FetchType


@Entity
@NamedQueries(value = [
    NamedQuery(name = "KindergardenGroup.getKindergardenGroups", query = "SELECT kg FROM KindergardenGroup kg WHERE kg.kindergarden.id = :kindergardenId AND kg.kindergarden.customer.id = :customerId"),
    NamedQuery(name = "KindergardenGroup.getKindergardenGroup", query = "SELECT kg FROM KindergardenGroup kg WHERE kg.id = :id AND kg.kindergarden.id = :kindergardenId AND kg.kindergarden.customer.id = :customerId")
]
)
@Table(name = "kindergardengroup")
data class KindergardenGroup(
        @Column(nullable = false)
        var name: String,
        @ManyToOne(optional = false)
        var kindergarden: Kindergarden
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null

    @OneToMany(mappedBy = "group", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    val childs: MutableList<Child> = mutableListOf()

    @OneToOne(mappedBy = "group", cascade = [CascadeType.ALL],
            fetch = FetchType.LAZY)
    lateinit var groupbook: Groupbook

    fun addChild(child: Child) {
        childs.add(child)
        child.group = this
    }

    fun removeChild(child: Child) {
        childs.remove(child)
    }

    fun addGroupbook(groupbook: Groupbook) {
        this.groupbook = groupbook
        groupbook.group = this
    }

}