package com.jachouni.mitabackend.controllers

import com.jachouni.mitabackend.KindergardenGroupDto
import com.jachouni.mitabackend.entities.KindergardenGroup
import com.jachouni.mitabackend.repositories.CustomerRepository
import com.jachouni.mitabackend.repositories.KindergardenGroupRepository
import com.jachouni.mitabackend.repositories.KindergardenRepository
import org.springframework.beans.factory.annotation.Autowired
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
        if (!customerRepository.existsById(customerId)) {
            throw EntityNotFoundException("Could not find customer with id: $customerId")
        }
        val kindergarden = kindergardenRepository.findById(kindergardenId)
        if (kindergarden.isPresent) {
            kindergardenGroupRepository.save(KindergardenGroup(name = dto.name, kindergarden = kindergarden.get()))
        } else {
            throw EntityNotFoundException("Could not find kindergarden with id: $kindergardenId")
        }
    }

}