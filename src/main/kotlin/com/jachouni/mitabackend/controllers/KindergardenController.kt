package com.jachouni.mitabackend.controllers

import com.jachouni.mitabackend.KindergardenDto
import com.jachouni.mitabackend.entities.Kindergarden
import com.jachouni.mitabackend.repositories.CustomerRepository
import com.jachouni.mitabackend.repositories.KindergardenRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class KindergardenController {
    @Autowired
    lateinit var kindergardenRepository: KindergardenRepository

    @Autowired
    lateinit var customerRepository: CustomerRepository


    @PostMapping(value = ["/customer/{customer-id}/kindergarden"])
    @ResponseBody
    fun createKindergarden(@PathVariable("customer-id") id: UUID, @RequestBody dto: KindergardenDto): ResponseEntity<Kindergarden> {
        val customer = customerRepository.findById(id).get()
        val kindergarden = Kindergarden(name = dto.name, customer = customer)
        kindergardenRepository.save(kindergarden)
        return ResponseEntity(kindergarden, HttpStatus.CREATED)
    }

}