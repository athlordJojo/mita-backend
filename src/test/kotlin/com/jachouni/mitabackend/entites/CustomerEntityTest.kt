package com.jachouni.mitabackend.entites

import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner


@RunWith(SpringRunner::class)
@DataJpaTest
class CustomerEntityTest :BaseTest() {

    @Test
    fun createCustomer() {
        super.createCustomerAndKindergarden()

        assertEquals(1, customerRepository!!.count())
    }
}