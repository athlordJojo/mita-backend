package com.jachouni.mitabackend.entites

import com.jachouni.mitabackend.CustomerDto
import com.jachouni.mitabackend.Sex
import com.jachouni.mitabackend.entities.*
import com.jachouni.mitabackend.repositories.*
import org.ajbrown.namemachine.Gender
import org.ajbrown.namemachine.NameGenerator
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.modelmapper.ModelMapper
import org.modelmapper.convention.MatchingStrategies
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDate
import java.time.LocalTime
import java.util.*


@RunWith(SpringRunner::class)
@DataJpaTest
class ModelTest {

    val generator = NameGenerator()

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
    lateinit var entryRepository: DayEntryRepository

    @Test
    fun roundTrip(){
        val generator = NameGenerator()
        val random = Random()
        val customer = Customer("Bürgerhaus")

        val kindergardens = 1
        val groupsPerKindergarden = 1
        val childsPerGroup = 1
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

        assertEquals(kindergardens, kindergardenRepository.count().toInt())

        val c1 = customerRepository.findById(customer.id!!).orElseThrow()

        val modelMapper = ModelMapper()
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        val dto = modelMapper.map(c1, CustomerDto::class.java)

        assertEquals(kindergardens * groupsPerKindergarden, kindergardenGroupRepository.count().toInt())
        assertEquals(kindergardens * groupsPerKindergarden, groupbookRepository.count().toInt())
        assertEquals(kindergardens * groupsPerKindergarden * childsPerGroup, childRepository.count().toInt())
        assertEquals(kindergardens * groupsPerKindergarden * daysPerGroupbook, dayRepository.count().toInt())
    }
}