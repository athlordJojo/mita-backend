package com.jachouni.mitabackend

import com.jachouni.mitabackend.entities.*
import com.jachouni.mitabackend.repositories.*
import org.ajbrown.namemachine.Gender
import org.ajbrown.namemachine.NameGenerator
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.LocalTime
import java.util.*


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

    @Autowired
    lateinit var childRepository: ChildRepository

    @Autowired
    lateinit var dayRepository: DayRepository


    override fun run(args: ApplicationArguments?) {

        val generator = NameGenerator()
        val random = Random()
        val customer = Customer("Bürgerhaus")

        val kindergardens = 10
        val groupsPerKindergarden = 10
        val childsPerGroup = 10
        val daysPerGroupbook = 2

        for (i in 1..kindergardens) {
            customer.addKindergarden(Kindergarden("Kita $i", customer))
        }

        customer.kindergardens.forEach {
            for (i in 1..groupsPerKindergarden) {
                val groupbook = Groupbook(name = "Groupbook of Group $i of kindergarden: ${it.name}")
                val group = KindergardenGroup(name = "Group $i of kindergarden: ${it.name}", kindergarden = it, groupbook = groupbook)
                it.addKindergardenGroup(group)

                for (k in 1..daysPerGroupbook) {
                    groupbook.addDay(Day(LocalDate.now().minusDays(k.toLong()), groupbook))
                }

                for (j in 1..childsPerGroup) {
                    val name = generator.generateName()
                    val sex: Sex = if (name.gender == Gender.MALE) Sex.MALE else Sex.FEMALE
                    val child = Child(name.firstName, name.lastName, LocalDate.now(), sex, group)
                    group.addChild(child)

                    for (day in groupbook.days) {
                        val arrivedAt: LocalTime? = if (random.nextInt() % 2 == 0) LocalTime.now().minusHours(random.nextInt(24).toLong()) else null
                        var leftAt: LocalTime? = null
                        if (arrivedAt != null) {
                            leftAt = if (random.nextInt() % 2 == 0) arrivedAt.plusHours(random.nextInt(8).toLong()) else null
                        }
                        day.addDayEntry(DayEntry(day, child, arrivedAt, leftAt))
                    }
                }
            }
        }

        customerRepository.save(customer)
        logger.info("Created ${kindergardenRepository.count()} kindergardens and ${kindergardenGroupRepository.count()} groups and ${groupbookRepository.count()} groupbooks and ${childRepository.count()} childs and ${dayRepository.count()} days")
    }
}