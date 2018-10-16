package com.jachouni.mitabackend.entites.repositories

import com.jachouni.mitabackend.entites.ModelBaseTest
import junit.framework.Assert.assertEquals
import org.junit.Test

class ChildRepositoryTest: ModelBaseTest() {
    @Test
    fun testFindAllByCustomerIdAndKindergardenId() {
        val customer = getCustomer()
        val kindergarden = customer.kindergardens[0]
        val kindergardenGroup = kindergarden.kindergardenGroups[0]

        val childs = childRepository.getChildsByCustomerIdAndKindergardenIdAndGroupId(customer.id!!, kindergarden.id!!, kindergardenGroup.id!!)
        assertEquals(childsPerGroup, childs.size)
    }

}