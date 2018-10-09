package com.jachouni.mitabackend.entites

import com.jachouni.mitabackend.entities.Customer
import com.jachouni.mitabackend.entities.Groupbook
import com.jachouni.mitabackend.entities.Kindergarden
import com.jachouni.mitabackend.entities.KindergardenGroup
import com.jachouni.mitabackend.repositories.CustomerRepository
import com.jachouni.mitabackend.repositories.GroupbookRepository
import com.jachouni.mitabackend.repositories.KindergardenGroupRepository
import com.jachouni.mitabackend.repositories.KindergardenRepository
import org.springframework.beans.factory.annotation.Autowired

abstract class BaseTest {

    @Autowired
    lateinit var kindergardenRepository: KindergardenRepository

    @Autowired
    lateinit var customerRepository: CustomerRepository

    @Autowired
    lateinit var kindergardenGroupRepository: KindergardenGroupRepository

    @Autowired
    lateinit var groupbookRepository: GroupbookRepository

    fun createCustomerAndKindergarden(): Pair<Customer, Kindergarden> {
        val customer = Customer("Bürgerhaus")
        customerRepository.save(customer)
        val kindergarden = Kindergarden("Wiesenwichtel", customer)

        kindergardenRepository.save(kindergarden)
        return Pair(customer, kindergarden)
    }

    fun createGroupAndGroupBook(pair: Pair<Customer, Kindergarden>): Pair<KindergardenGroup, Groupbook> {
        val (customer, kindergarden) = pair
        val kindergardenGroup = KindergardenGroup(name = "Group1", kindergarden = kindergarden)
        kindergardenGroupRepository.save(kindergardenGroup)

        val groupbook = Groupbook(name = "Groupbook of group1", group = kindergardenGroup)
        groupbookRepository.save(groupbook)

        return Pair(kindergardenGroup, groupbook)
    }

}