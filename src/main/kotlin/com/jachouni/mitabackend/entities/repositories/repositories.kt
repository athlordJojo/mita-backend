package com.jachouni.mitabackend.entities.repositories

import com.jachouni.mitabackend.entities.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import java.util.*

interface CustomerRepository : JpaRepository<Customer, UUID>

interface KindergardenRepository : JpaRepository<Kindergarden, UUID> {
    fun findByIdAndCustomer_id(kindergardenId: UUID, customerId: UUID): Kindergarden
    fun findAllByCustomer_id(customerId: UUID): List<Kindergarden>
}

interface KindergardenGroupRepository : JpaRepository<KindergardenGroup, UUID> {
    fun findAllByCustomerIdAndKindergardenId(@Param("customerId") customerId: UUID, @Param("kindergardenId") kindergardenId: UUID): List<KindergardenGroup>
    fun findByIdAndCustomerIdAndKindergardenId(@Param("customerId") customerId: UUID, @Param("kindergardenId") kindergardenId: UUID, @Param("id") id: UUID): KindergardenGroup
}

interface GroupbookRepository : JpaRepository<Groupbook, UUID>

interface DayRepository : JpaRepository<Day, UUID>

interface DayEntryRepository : JpaRepository<DayEntry, UUID>

interface ChildRepository : JpaRepository<Child, UUID> {
    fun getChilds(@Param("customerId") customerId: UUID,
                  @Param("kindergardenId") kindergardenId: UUID,
                  @Param("kindergardenGroupId") kindergardenGroupId: UUID): List<Child>

    fun getChild(@Param("customerId") customerId: UUID,
                 @Param("kindergardenId") kindergardenId: UUID,
                 @Param("kindergardenGroupId") kindergardenGroupId: UUID,
                 @Param("childId") childId: UUID): Child
}



