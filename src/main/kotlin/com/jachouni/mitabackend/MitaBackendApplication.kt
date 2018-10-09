package com.jachouni.mitabackend

import com.jachouni.mitabackend.entities.Customer
import com.jachouni.mitabackend.entities.Groupbook
import com.jachouni.mitabackend.entities.Kindergarden
import com.jachouni.mitabackend.entities.KindergardenGroup
import com.jachouni.mitabackend.repositories.CustomerRepository
import com.jachouni.mitabackend.repositories.GroupbookRepository
import com.jachouni.mitabackend.repositories.KindergardenGroupRepository
import com.jachouni.mitabackend.repositories.KindergardenRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component


@SpringBootApplication
class MitaBackendApplication() {

}

fun main(args: Array<String>) {
    runApplication<MitaBackendApplication>(*args)
}

@Component
class DataCreator : ApplicationRunner {
    var logger: Logger = LoggerFactory.getLogger(DataCreator::class.java)

    @Autowired
    lateinit var kindergardenRepository: KindergardenRepository

    @Autowired
    lateinit var customerRepository: CustomerRepository

    @Autowired
    lateinit var kindergardenGroupRepository: KindergardenGroupRepository

    @Autowired
    lateinit var groupbookRepository: GroupbookRepository

    override fun run(args: ApplicationArguments?) {

        val customer = Customer("Bürgerhaus")
        customerRepository.save(customer)

        val kindergardens = mutableListOf<Kindergarden>()
        for (i in 1..10) {
            kindergardens.add(Kindergarden("Kita $i", customer))

        }
        kindergardenRepository.saveAll(kindergardens)


        val groups = mutableListOf<KindergardenGroup>()

        kindergardens.forEach {
            for (i in 1..10) {
                groups.add(KindergardenGroup(name = "Group $i of kindergarden: ${it.name}", kindergarden = it))
            }
        }

        kindergardenGroupRepository.saveAll(groups)


        val groupbooks = mutableListOf<Groupbook>()
        groups.forEach {
            groupbooks.add(Groupbook(name = "Groupbook of group: ${it.name}", group = it))
        }

        groupbookRepository.saveAll(groupbooks)
        logger.info("Created ${kindergardenRepository.count()} kindergardens and ${kindergardenGroupRepository.count()} groups and ${groupbookRepository.count()} groupbooks")
    }
}