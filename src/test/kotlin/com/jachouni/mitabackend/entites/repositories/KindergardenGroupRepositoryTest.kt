package com.jachouni.mitabackend.entites.repositories

import com.jachouni.mitabackend.entites.ModelBaseTest
import com.jachouni.mitabackend.entities.Kindergarden
import junit.framework.Assert.assertEquals
import org.junit.Test

class KindergardenGroupRepositoryTest : ModelBaseTest() {

    @Test
    fun testFindAllByCustomerIdAndKindergardenId() {
        val customer = getCustomer()
        customer.kindergardens.forEach { kindergarden: Kindergarden ->
            assertEquals(this.groupsPerKindergarden, kindergardenGroupRepository.getKindergardenGroups(kindergarden.customer.id!!, kindergarden.id!!).size)
        }
    }

    @Test
    fun testAllByCustomerIdAndKindergardenId() {
        val customer = getCustomer()
        val kindergarden = customer.kindergardens[0]
        val kindergardenGroup = kindergarden.kindergardenGroups[0]

        val result = kindergardenGroupRepository.getKindergardenGroup(customerId = customer.id!!, kindergardenId = kindergarden.id!!, id = kindergardenGroup.id!!)
        assertEquals(kindergardenGroup.id!!, result.id!!)
        assertEquals(kindergardenGroup.name, result.name)
    }
}