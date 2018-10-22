package com.jachouni.mitabackend.entites.repositories

import com.jachouni.mitabackend.entites.ModelBaseTest
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test

class DayEntryRepositoryTest : ModelBaseTest() {

    @Test
    fun testGetEntriesOfChild() {
        val customer = getCustomer()
        val kindergarden = customer.kindergardens[0]
        val kindergardenGroup = kindergarden.kindergardenGroups[0]
        val child = kindergardenGroup.childs[0]
        val entriesOfChild = dayEntryRepository.getEntriesOfChild(customerId = customer.id!!, kindergardenId = kindergarden.id!!, kindergardenGroupId = kindergardenGroup.id!!, childId = child.id!!)
        assertEquals(child.dayEntries.size, entriesOfChild.size)
    }
}