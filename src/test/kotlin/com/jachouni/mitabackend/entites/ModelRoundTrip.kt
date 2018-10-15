package com.jachouni.mitabackend.entites

import org.junit.Assert.assertEquals
import org.junit.Test

class ModelRoundTrip: ModelBaseTest() {

    @Test
    fun roundTrip(){
        getCustomer()
        assertEquals(kindergardens, kindergardenRepository.count().toInt())
        assertEquals(kindergardens * groupsPerKindergarden, kindergardenGroupRepository.count().toInt())
        assertEquals(kindergardens * groupsPerKindergarden, groupbookRepository.count().toInt())
        assertEquals(kindergardens * groupsPerKindergarden * childsPerGroup, childRepository.count().toInt())
        assertEquals(kindergardens * groupsPerKindergarden * daysPerGroupbook, dayRepository.count().toInt())
        assertEquals(kindergardens * groupsPerKindergarden * daysPerGroupbook * childsPerGroup, dayEntryRepository.count().toInt())
    }
}