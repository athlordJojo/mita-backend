package com.jachouni.mitabackend.repositories

import com.jachouni.mitabackend.entities.Customer
import com.jachouni.mitabackend.entities.Kindergarden
import org.springframework.data.repository.PagingAndSortingRepository
import java.util.*

interface CustomerRepository : PagingAndSortingRepository<Customer, UUID> {
}


interface KindergardenRepository : PagingAndSortingRepository<Kindergarden, UUID> {
}