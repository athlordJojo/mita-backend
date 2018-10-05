package com.jachouni.mitabackend.controllers

import com.jachouni.mitabackend.CustomerDto
import com.jachouni.mitabackend.entities.Customer
import com.jachouni.mitabackend.repositories.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.rest.webmvc.RepositoryRestController
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody

@RepositoryRestController
class CustomerController() {
    @Autowired
    lateinit var customerRepository: CustomerRepository


    @PostMapping(value = ["/customer"])
    @ResponseBody
    fun createCustomer(@RequestBody dto: CustomerDto): Customer {
        val c = Customer(null, dto.name)
        customerRepository.save(c)
        return c
    }
}