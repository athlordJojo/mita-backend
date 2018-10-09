package com.jachouni.mitabackend.entites

import com.jachouni.mitabackend.entities.Customer
import com.jachouni.mitabackend.entities.Groupbook
import com.jachouni.mitabackend.entities.Kindergarden
import com.jachouni.mitabackend.entities.KindergardenGroup
import com.jachouni.mitabackend.repositories.CustomerRepository
import com.jachouni.mitabackend.repositories.GroupbookRepository
import com.jachouni.mitabackend.repositories.KindergardenGroupRepository
import com.jachouni.mitabackend.repositories.KindergardenRepository
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@DataJpaTest
class GroupbookEntityTest {
    @Autowired
    private val kindergardenRepository: KindergardenRepository? = null

    @Autowired
    private val customerRepository: CustomerRepository? = null

    @Autowired
    val kindergardenGroupRepository: KindergardenGroupRepository? = null

    @Autowired
    val groubookRepository: GroupbookRepository? = null

    var customer: Customer? = null
    var kindergarden: Kindergarden? = null

    @Before
    fun before() {
        val customer = Customer("bürgerhaus")
        customerRepository!!.save(customer)
        val k1 = Kindergarden("Wiesenwichtel", customer)

        kindergardenRepository!!.save(k1)

        this.customer = customer
        this.kindergarden = k1
    }

    @Test
    fun groupbookTest() {
        val kindergardenGroup = KindergardenGroup(name = "g1", kindergarden = kindergarden!!)
        kindergardenGroupRepository?.save(kindergardenGroup)

        val groupbook = Groupbook("book of group g1", kindergardenGroup)
        groubookRepository!!.save(groupbook)
        assertEquals(1L, groubookRepository!!.count())
    }
}