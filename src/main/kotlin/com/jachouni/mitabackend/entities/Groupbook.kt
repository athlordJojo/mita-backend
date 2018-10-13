package com.jachouni.mitabackend.entities

import java.time.LocalDate
import java.time.LocalTime
import java.util.*
import javax.persistence.*

@Entity
data class Groupbook(
        @Column(nullable = false)
        var name: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "groupbook", cascade = [CascadeType.ALL])
    var days: MutableList<Day> = mutableListOf()

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    var group: KindergardenGroup? = null

    fun addDay(day: Day) {
        days.add(day)
    }

    fun removeDay(day: Day) {
        days.remove(day)
    }
}

@Entity
data class Day(
        @Column(nullable = false)
        var date: LocalDate,
        @ManyToOne
        val groupbook: Groupbook
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "day", cascade = [CascadeType.ALL])
    var entries: MutableList<DayEntry> = mutableListOf()

    fun addDayEntry(entry:DayEntry){
        entries.add(entry)
    }

    fun removeDayEntry(entry:DayEntry){
        entries.remove(entry)
    }
}

@Entity
@Table(uniqueConstraints = [UniqueConstraint(columnNames = ["day_id", "child_id"])])
data class DayEntry(

        @ManyToOne(optional = false)
        val day: Day,

        @ManyToOne(optional = false)
        val child: Child,

        @Column(nullable = true)
        var arrivedAt: LocalTime? = null,

        @Column(nullable = true)
        var leftAt: LocalTime? = null
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null
}