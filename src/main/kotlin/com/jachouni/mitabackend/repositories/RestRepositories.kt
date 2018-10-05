package com.jachouni.mitabackend.repositories

import com.jachouni.mitabackend.entities.Customer
import com.jachouni.mitabackend.entities.Kindergarden
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import java.util.*

@RepositoryRestResource(collectionResourceRel = "customer", path = "customer")
interface CustomerRepository : PagingAndSortingRepository<Customer, UUID> {
}


@RepositoryRestResource(collectionResourceRel = "kindergarden", path = "kindergarden")
interface KindergardenRepository : PagingAndSortingRepository<Kindergarden, UUID> {
}