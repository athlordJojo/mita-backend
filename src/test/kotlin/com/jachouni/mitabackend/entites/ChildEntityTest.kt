package com.jachouni.mitabackend.entites

import com.jachouni.mitabackend.entities.Child
import com.jachouni.mitabackend.entities.Customer
import com.jachouni.mitabackend.entities.Kindergarden
import com.jachouni.mitabackend.entities.KindergardenGroup
import com.jachouni.mitabackend.repositories.ChildRepository
import com.jachouni.mitabackend.repositories.KindergardenGroupRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@DataJpaTest
class ChildEntityTest:BaseTest() {

    var customer:Customer? = null
    var kindergarden: Kindergarden? = null

    @Autowired
    val childRepository:ChildRepository? = null

    @Before
    fun before(){
        val createCustomerAndKindergarden = super.createCustomerAndKindergarden()
        customer=createCustomerAndKindergarden.first
        kindergarden = createCustomerAndKindergarden.second
    }

    @Test
    fun createChild(){
        val kindergardenGroup = KindergardenGroup(name = "Group 1", kindergarden = kindergarden!!)
        kindergardenGroupRepository!!.save(kindergardenGroup)
        val child1 = Child(firstname = "Peter", lastname = "Enis", kindergardenGroup = kindergardenGroup)
        val child2 = Child(firstname = "Hans", lastname = "Wurst", kindergardenGroup = kindergardenGroup)

        childRepository!!.save(child1)
        childRepository!!.save(child2)

        Assert.assertEquals(2L, childRepository!!.count())
        childRepository!!.findById(child1.id!!)

    }
}