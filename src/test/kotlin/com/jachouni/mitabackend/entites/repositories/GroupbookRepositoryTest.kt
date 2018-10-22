package com.jachouni.mitabackend.entites.repositories

import com.jachouni.mitabackend.entites.ModelBaseTest
import org.junit.Assert.assertEquals
import org.junit.Test


class GroupbookRepositoryTest : ModelBaseTest() {

    @Test
    fun testGetgroupbook() {
        val customer = getCustomer()
        val kindergardenGroup = customer.kindergardens[0].kindergardenGroups[0]
        val groupbook = groupbookRepository.getGroupbook(customerId = customer.id!!, kindergardenId = customer.kindergardens[0].id!!, kindergardenGroupId = kindergardenGroup.id!!)
        assertEquals(kindergardenGroup.groupbook.id, groupbook.id)
        assertEquals(kindergardenGroup.groupbook.name, groupbook.name)
    }

}