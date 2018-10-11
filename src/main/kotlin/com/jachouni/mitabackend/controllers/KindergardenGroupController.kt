package com.jachouni.mitabackend.controllers

import com.jachouni.mitabackend.KindergardenGroupDto
import com.jachouni.mitabackend.entities.Groupbook
import com.jachouni.mitabackend.entities.KindergardenGroup
import com.jachouni.mitabackend.repositories.CustomerRepository
import com.jachouni.mitabackend.repositories.KindergardenGroupRepository
import com.jachouni.mitabackend.repositories.KindergardenRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.persistence.EntityNotFoundException


@RestController
class KindergardenGroupController {


    @Autowired
    lateinit var customerRepository: CustomerRepository

    @Autowired
    lateinit var kindergardenRepository: KindergardenRepository

    @Autowired
    lateinit var kindergardenGroupRepository: KindergardenGroupRepository

    @PostMapping(value = ["/customers/{customer-id}/kindergardens/{kindergarden-id}/kindergardengroup"])
    @ResponseBody
    fun createKindergardenGroup(@PathVariable("customer-id") customerId: UUID, @PathVariable("kindergarden-id") kindergardenId: UUID, @RequestBody dto: KindergardenGroupDto) {
        checkCustomer(customerId)
        val kindergarden = kindergardenRepository.findById(kindergardenId)
        if (kindergarden.isPresent) {
            val k = kindergarden.get()
            k.addKindergardenGroup(KindergardenGroup(name = dto.name, kindergarden = k, groupbook = Groupbook("Groubbook of group ${k.name}")))
        } else {
            throw EntityNotFoundException("Could not find kindergarden with id: $kindergardenId")
        }
    }

    private fun checkCustomer(customerId: UUID) {
        if (!customerRepository.existsById(customerId)) {
            throw EntityNotFoundException("Could not find customer with id: $customerId")
        }
    }

    @GetMapping(value = ["/customers/{customer-id}/kindergardens/{kindergarden-id}/kindergardengroup"])
    @ResponseBody
    fun getKindergardenGroups(@PathVariable("customer-id") customerId: UUID, @PathVariable("kindergarden-id") kindergardenId: UUID): ResponseEntity<List<KindergardenGroupDto>> {
        checkCustomer(customerId)
        val dtos = kindergardenGroupRepository.findAll().toMutableList().map { KindergardenGroupDto(id = it.id, name = it.name) }.toList()
        return ResponseEntity(dtos, HttpStatus.CREATED)
    }

}