package com.jachouni.mitabackend.entities

import com.jachouni.mitabackend.CustomerDto
import java.util.*
import javax.persistence.*

@Entity
data class Customer(
        @Column(nullable = false)
        var name: String) {

    @OneToMany(mappedBy = "customer", orphanRemoval = true, fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val kindergardens: MutableList<Kindergarden> = mutableListOf()

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null

    fun addKindergarden(kindergarden: Kindergarden){
        kindergardens.add(kindergarden)
        kindergarden.customer = this
    }

    fun removeKindergarden(kindergarden: Kindergarden){
        kindergardens.remove(kindergarden)
    }

}