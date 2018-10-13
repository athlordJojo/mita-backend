package com.jachouni.mitabackend.repositories

import com.jachouni.mitabackend.entities.*
import org.springframework.data.repository.PagingAndSortingRepository
import java.util.*

interface CustomerRepository : PagingAndSortingRepository<Customer, UUID>

interface KindergardenRepository : PagingAndSortingRepository<Kindergarden, UUID> {
    fun findByCustomer(customer: Customer): List<Kindergarden>
}

interface KindergardenGroupRepository : PagingAndSortingRepository<KindergardenGroup, UUID>

interface GroupbookRepository : PagingAndSortingRepository<Groupbook, UUID>

interface DayRepository : PagingAndSortingRepository<Day, UUID>

interface DayEntryRepository : PagingAndSortingRepository<DayEntry, UUID>

interface ChildRepository : PagingAndSortingRepository<Child, UUID>



