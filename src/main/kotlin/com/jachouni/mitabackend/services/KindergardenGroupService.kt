package com.jachouni.mitabackend.services

import com.jachouni.mitabackend.entities.repositories.KindergardenGroupRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class KindergardenGroupService(val kindergardenGroupRepository: KindergardenGroupRepository) {

    fun getAllKindergarden(customerId: UUID, kindergardenId: UUID) {
        kindergardenGroupRepository.findByCustomerIdAndKindergardenId(customerId, kindergardenId)
    }
}