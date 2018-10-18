package com.jachouni.mitabackend.services

import com.jachouni.mitabackend.entities.KindergardenGroup
import com.jachouni.mitabackend.entities.repositories.KindergardenGroupRepository
import com.jachouni.mitabackend.entities.repositories.KindergardenRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class KindergardenGroupService(
        @Autowired
        val kindergardenGroupRepository: KindergardenGroupRepository,
        @Autowired
        val kindergardenRepository: KindergardenRepository
) {

    fun createKindergardenGroup(customerId: UUID, kindergardenId: UUID, kindergardenGroup: KindergardenGroup): KindergardenGroup {
        val kindergarden = kindergardenRepository.findByIdAndCustomer_id(kindergardenId = kindergardenId, customerId = customerId)
        kindergarden.addKindergardenGroup(kindergardenGroup)
        return kindergardenGroupRepository.save(kindergardenGroup)
    }

    fun getAllKindergardenGroups(customerId: UUID, kindergardenId: UUID): List<KindergardenGroup> {
        return kindergardenGroupRepository.findAllByCustomerIdAndKindergardenId(customerId, kindergardenId)
    }

    fun getKindergardenGroup(customerId: UUID, kindergardenId: UUID, id: UUID): KindergardenGroup {
        return kindergardenGroupRepository.findByIdAndCustomerIdAndKindergardenId(customerId, kindergardenId, id)
    }
}