package com.jachouni.mitabackend.services

import com.jachouni.mitabackend.entities.Child
import com.jachouni.mitabackend.entities.repositories.ChildRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class ChildService(@Autowired val childRepository: ChildRepository) {
    fun getChildsOfGroup(customerId: UUID, kindergardenId: UUID, kindergardenGroupId: UUID): List<Child> {
        return childRepository.getChildsByCustomerIdAndKindergardenIdAndGroupId(customerId, kindergardenId, kindergardenGroupId)
    }
}