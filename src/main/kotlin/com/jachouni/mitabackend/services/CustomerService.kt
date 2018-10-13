package com.jachouni.mitabackend.services

import com.jachouni.mitabackend.entities.Customer
import com.jachouni.mitabackend.repositories.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class CustomerService(@Autowired var customerRepository: CustomerRepository) {

    fun createCustomer(customer: Customer): Customer {
        customerRepository.save(customer)
        return customer
    }

    fun getAllCustomers(): List<Customer> {
        return customerRepository.findAll().toList()
    }

    fun getById(id: UUID): Customer {
        return customerRepository.findById(id).orElseThrow()
    }
}