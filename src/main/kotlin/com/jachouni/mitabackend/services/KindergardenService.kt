package com.jachouni.mitabackend.services

import com.jachouni.mitabackend.entities.Kindergarden
import com.jachouni.mitabackend.repositories.CustomerRepository
import com.jachouni.mitabackend.repositories.KindergardenRepository
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
        customerRepository.save(customer)

        return kindergarden
    }

    fun getAllKinderGardens(customerId: UUID): List<Kindergarden> {
        val customer = customerRepository.findById(customerId).orElseThrow()
        return kindergardenRepository.findByCustomer(customer)
    }

}