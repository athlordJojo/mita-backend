package com.jachouni.mitabackend.entities

import java.time.LocalDate
import java.util.*
import javax.persistence.*

@NamedQueries(value = [
    NamedQuery(name = "Child.getChildsByCustomerIdAndKindergardenIdAndGroupId", query = "SELECT c FROM Child c WHERE c.kindergardenGroup.id = :kindergardenGroupId AND c.kindergardenGroup.kindergarden.id = :kindergardenId AND c.kindergardenGroup.kindergarden.customer.id = :customerId")
]
)
@Entity
data class Child(
        @Column(nullable = false)
        var firstname: String,
        @Column(nullable = false)
        var lastname: String,
        @Column(nullable = false)
        var birthday: LocalDate,
        @Enumerated
        @Column
        var sex: Sex,
        @ManyToOne(optional = false)
        var kindergardenGroup: KindergardenGroup
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID? = null

    var isOnVacation: Boolean = false

    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "child")
    val dayEntries: MutableList<DayEntry> = mutableListOf()

    fun addDayEntry(dayEntry: DayEntry) {
        dayEntries.add(dayEntry)
    }

    fun removeDayEntry(dayEntry: DayEntry) {
        dayEntries.remove(dayEntry)
    }
}