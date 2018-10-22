package com.jachouni.mitabackend.entites

import com.jachouni.mitabackend.entities.Sex
import com.jachouni.mitabackend.entities.*
import com.jachouni.mitabackend.entities.repositories.*
import org.ajbrown.namemachine.Gender
import org.ajbrown.namemachine.NameGenerator
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDate
import java.time.LocalTime
import java.util.*


@RunWith(SpringRunner::class)
@DataJpaTest
abstract class ModelBaseTest {

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

    @Autowired
    lateinit var dayEntryRepository: DayEntryRepository

    val kindergardens = 1
    val groupsPerKindergarden = 10
    val childsPerGroup = 10
    val daysPerGroupbook = 2


    fun getCustomer(): Customer {
        val generator = NameGenerator()
        val random = Random()
        val customer = Customer("Bürgerhaus")


        for (i in 1..kindergardens) {
            customer.addKindergarden(Kindergarden("Kita $i", customer))
        }

        customer.kindergardens.forEach {
            for (i in 1..groupsPerKindergarden) {
                val groupbook = Groupbook(name = "Groupbook of Group $i of kindergarden: ${it.name}")
                val group = KindergardenGroup(name = "Group $i of kindergarden: ${it.name}", kindergarden = it)
                group.addGroupbook(groupbook)
                it.addKindergardenGroup(group)

                for (k in 1..daysPerGroupbook) {
                    groupbook.addDay(Day(LocalDate.now().minusDays(k.toLong()), groupbook))
                }

                for (j in 1..childsPerGroup) {
                    val name = generator.generateName()
                    val sex: Sex = if (name.gender == Gender.MALE) Sex.MALE else Sex.FEMALE
                    val child = Child(name.firstName, name.lastName, LocalDate.now(), sex, group)
                    group.addChild(child)

                    groupbook.days.forEach {
                        val arrivedAt: LocalTime? = if (random.nextInt() % 2 == 0) LocalTime.now().minusHours(random.nextInt(24).toLong()) else null
                        var leftAt: LocalTime? = null
                        if (arrivedAt != null) {
                            leftAt = if (random.nextInt() % 2 == 0) arrivedAt.plusHours(random.nextInt(8).toLong()) else null
                        }
                        it.addDayEntry(DayEntry(it, child, arrivedAt, leftAt))

                    }
                }
            }
        }

        customerRepository.saveAndFlush(customer)
        return customer
    }
}