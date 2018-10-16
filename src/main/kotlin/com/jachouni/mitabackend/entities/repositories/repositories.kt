package com.jachouni.mitabackend.entities.repositories

import com.jachouni.mitabackend.entities.*
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import java.util.*

interface CustomerRepository : PagingAndSortingRepository<Customer, UUID>

interface KindergardenRepository : PagingAndSortingRepository<Kindergarden, UUID> {
    fun findByIdAndCustomer_id(kindergardenId: UUID, customerId: UUID): Kindergarden
    fun findAllByCustomer(customer: Customer): List<Kindergarden>
}

interface KindergardenGroupRepository : PagingAndSortingRepository<KindergardenGroup, UUID> {
    fun findAllByCustomerIdAndKindergardenId(@Param("customerId") customerId: UUID, @Param("kindergardenId") kindergardenId: UUID): List<KindergardenGroup>
}

interface GroupbookRepository : PagingAndSortingRepository<Groupbook, UUID>

interface DayRepository : PagingAndSortingRepository<Day, UUID>

interface DayEntryRepository : PagingAndSortingRepository<DayEntry, UUID>

interface ChildRepository : PagingAndSortingRepository<Child, UUID>



