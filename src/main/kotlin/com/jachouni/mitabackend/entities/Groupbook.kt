package com.jachouni.mitabackend.entities

import java.time.LocalDate
import java.util.*
import javax.persistence.*

@Entity
data class Groupbook(val name: String,
                     @OneToOne(fetch = FetchType.LAZY)
                     val group: KindergardenGroup) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "groupbook")
    var days: List<Day> = mutableListOf()
}

@Entity
data class Day(
        val name: LocalDate,
        @ManyToOne
        val groupbook: Groupbook
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "day")
    var entries: List<DayEntry> = mutableListOf()
}

@Entity
@Table(uniqueConstraints = [UniqueConstraint(columnNames = ["day_id", "child_id"])])
data class DayEntry(

        @ManyToOne
        val day: Day,

        @ManyToOne
        val child: Child
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null
}