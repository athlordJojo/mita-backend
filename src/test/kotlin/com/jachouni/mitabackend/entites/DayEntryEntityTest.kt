package com.jachouni.mitabackend.entites

import com.jachouni.mitabackend.entities.*
import com.jachouni.mitabackend.repositories.ChildRepository
import com.jachouni.mitabackend.repositories.DayEntryRepository
import com.jachouni.mitabackend.repositories.DayRepository
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDate
import java.time.ZoneId

@RunWith(SpringRunner::class)
@DataJpaTest
class DayEntryEntityTest : BaseTest() {

    lateinit var customer: Customer
    lateinit var kindergarden: Kindergarden
    lateinit var kindergardenGroup: KindergardenGroup
    lateinit var groupbook: Groupbook
    lateinit var child1: Child
    lateinit var child2: Child

    @Autowired
    lateinit var dayEntryRepository: DayEntryRepository

    @Autowired
    lateinit var dayRepository: DayRepository

    @Autowired
    lateinit var childRepository: ChildRepository

    @Before
    fun before() {
        val customerAndKindergarden = createCustomerAndKindergarden()
        val groupAndGroupBook = createGroupAndGroupBook(customerAndKindergarden)

        customer = customerAndKindergarden.first
        kindergarden = customerAndKindergarden.second
        kindergardenGroup = groupAndGroupBook.first
        groupbook = groupAndGroupBook.second
        child1 = Child(firstname = "hans", lastname = "wurst", kindergardenGroup = kindergardenGroup)
        childRepository.save(child1)

        child2 = Child(firstname = "peter", lastname = "enis", kindergardenGroup = kindergardenGroup)
        childRepository.save(child2)
    }


    @Test
    fun createDayEntry(){
        val day = Day(LocalDate.now(ZoneId.of("Europe/Paris")), groupbook)
        dayRepository.save(day)
        val dayEntry1 = DayEntry(child = child1, day = day)
        dayEntryRepository.save(dayEntry1)

        val dayEntry2 = DayEntry(child = child2, day = day)
        dayEntryRepository.save(dayEntry2)
        assertEquals(2, dayEntryRepository.count())
    }

    @Test(expected = org.springframework.dao.DataIntegrityViolationException::class)
    fun createDayEntryDuplication(){
        val day = Day(LocalDate.now(ZoneId.of("Europe/Paris")), groupbook)
        dayRepository.save(day)
        val dayEntry1 = DayEntry(child = child1, day = day)
        dayEntryRepository.save(dayEntry1)

        val dayEntry2 = DayEntry(child = child1, day = day)
        dayEntryRepository.save(dayEntry2)
        assertEquals(2, dayEntryRepository.count())
    }
}