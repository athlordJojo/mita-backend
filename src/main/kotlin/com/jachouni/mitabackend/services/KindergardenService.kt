package com.jachouni.mitabackend.services

import com.jachouni.mitabackend.entities.Kindergarden
import com.jachouni.mitabackend.entities.repositories.CustomerRepository
import com.jachouni.mitabackend.entities.repositories.KindergardenRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class KindergardenService(
        @Autowired
        var customerRepository: CustomerRepository,
        @Autowired
        var kindergardenRepository: KindergardenRepository) {

    fun createKindergarden(customerId: UUID, kindergarden: Kindergarden): Kindergarden {
        val customer = customerRepository.findById(customerId).orElseThrow()
        customer.addKindergarden(kindergarden)
        return kindergardenRepository.save(kindergarden)
    }

    fun getAllKinderGardens(customerId: UUID): List<Kindergarden> {
        return kindergardenRepository.findAllByCustomer_id(customerId)
    }

}