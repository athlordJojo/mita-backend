package com.jachouni.mitabackend.entities

import java.time.LocalDate
import java.util.*
import javax.persistence.*

@Entity
data class Child(
        @Column(nullable = false)
        val firstname: String,
        @Column(nullable = false)
        val lastname: String,
        @Column(nullable = false)
        val birthday: LocalDate,
        @ManyToOne(optional = false)
        var kindergardenGroup: KindergardenGroup? = null
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID? = null

    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "child")
    val dayEntries: MutableList<DayEntry> = mutableListOf()

    fun addDayEntry(dayEntry: DayEntry) {
        dayEntries.add(dayEntry)
    }

    fun removeDayEntry(dayEntry: DayEntry) {
        dayEntries.remove(dayEntry)
    }
}