package com.jachouni.mitabackend.entities

import java.time.LocalDate
import java.time.LocalTime
import java.util.*
import javax.persistence.*

@Entity
data class Groupbook(
        @Column(nullable = false)
        val name: String
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
        day.groupbook = this
    }

    fun removeDay(day: Day) {
        days.remove(day)
    }
}

@Entity
@Table(uniqueConstraints = [UniqueConstraint(columnNames = ["date", "groupbook_id"])])
data class Day(
        @Column(nullable = false, name = "date")
        val date: LocalDate,
        @ManyToOne
        @JoinColumn(name = "groupbook_id")
        var groupbook: Groupbook
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "day", cascade = [CascadeType.ALL])
    var entries: MutableList<DayEntry> = mutableListOf()

    fun addDayEntry(entry:DayEntry){
        entries.add(entry)
        entry.day = this
    }

    fun removeDayEntry(entry:DayEntry){
        entries.remove(entry)
    }
}

@Entity
@Table(uniqueConstraints = [UniqueConstraint(columnNames = ["day_id", "child_id"])])
data class DayEntry(

        @ManyToOne(optional = false)
        @JoinColumn(name = "day_id")
        var day: Day,

        @ManyToOne(optional = false)
        @JoinColumn(name = "child_id")
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