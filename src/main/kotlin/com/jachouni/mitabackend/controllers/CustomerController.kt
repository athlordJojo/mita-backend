package com.jachouni.mitabackend.controllers

import com.jachouni.mitabackend.CustomerDto
import com.jachouni.mitabackend.entities.Customer
import com.jachouni.mitabackend.repositories.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class CustomerController {
    @Autowired
    lateinit var customerRepository: CustomerRepository


    @PostMapping(value = ["/customers"])
    @ResponseBody
    fun createCustomer(@RequestBody entity: CustomerDto): ResponseEntity<Customer> {
        val c = Customer(null, entity.name)
        customerRepository.save(c)
        return ResponseEntity.ok(c)
    }

    @GetMapping(value = ["/customers"])
    @ResponseBody
    fun getAllCustomer(): List<CustomerDto> {
        return customerRepository.findAll().map { customer -> customer.toDto() }.toList().requireNoNulls()
    }
}