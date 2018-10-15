package com.jachouni.mitabackend.entites.repositories

import com.jachouni.mitabackend.entites.ModelBaseTest
import com.jachouni.mitabackend.entities.Kindergarden
import junit.framework.Assert.assertEquals
import org.junit.Test

class KindergardenGroupRepositoryTest : ModelBaseTest() {

    @Test
    fun testFindByCustomerIdAndKindergardenId() {
        val customer = getCustomer()
        customer.kindergardens.forEach { kindergarden: Kindergarden ->
            assertEquals(this.groupsPerKindergarden, kindergardenGroupRepository.findByCustomerIdAndKindergardenId(kindergarden.customer.id!!, kindergarden.id!!).size)
        }
    }
}