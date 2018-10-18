package com.jachouni.mitabackend.controllers

import com.jachouni.mitabackend.CustomerDto
import com.jachouni.mitabackend.entities.Customer
import com.jachouni.mitabackend.services.CustomerService
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
class CustomerController(@Autowired
                         var customerService: CustomerService) {

    @PostMapping(value = ["/customers"])
    @ResponseBody
    fun createCustomer(@RequestBody dto: CustomerDto): ResponseEntity<CustomerDto> {
        val modelMapper = ModelMapper()
        val customer = modelMapper.map(dto, Customer::class.java)
        val createdCustomer = customerService.createCustomer(customer)

        return ResponseEntity(modelMapper.map(createdCustomer, CustomerDto::class.java), HttpStatus.CREATED)
    }

    @GetMapping(value = ["/customers"])
    @ResponseBody
    fun getAllCustomers(): ResponseEntity<List<CustomerDto>> {
        val modelMapper = ModelMapper()
        val customers = customerService.getAllCustomers()
        val dtos = customers.map { customer -> modelMapper.map(customer, CustomerDto::class.java) }.toList()
        return ResponseEntity.ok(dtos)
    }

    @GetMapping(value = ["/customers/{customer-id}"])
    @ResponseBody
    fun getCustomer(@PathVariable("customer-id") id: UUID): ResponseEntity<CustomerDto> {
        val customer = customerService.getById(id)
        val modelMapper = ModelMapper()
        return ResponseEntity.ok(modelMapper.map(customer, CustomerDto::class.java))

    }
}