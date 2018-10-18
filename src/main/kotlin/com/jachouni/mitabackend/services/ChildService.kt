package com.jachouni.mitabackend.services

import com.jachouni.mitabackend.entities.Child
import com.jachouni.mitabackend.entities.repositories.ChildRepository
import com.jachouni.mitabackend.entities.repositories.KindergardenGroupRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class ChildService(@Autowired val childRepository: ChildRepository,
                   @Autowired val kindergardenGroupRepository: KindergardenGroupRepository) {
    fun getChildsOfGroup(customerId: UUID, kindergardenId: UUID, kindergardenGroupId: UUID): List<Child> {
        return childRepository.getChilds(customerId, kindergardenId, kindergardenGroupId)
    }

    fun getChildOfGroup(customerId: UUID, kindergardenId: UUID, kindergardenGroupId: UUID, childId: UUID): Child {
        return childRepository.getChild(customerId, kindergardenId, kindergardenGroupId, childId)
    }

    fun createChild(customerId: UUID, kindergardenId: UUID, kindergardenGroupId: UUID, child: Child): Child {
        val group = kindergardenGroupRepository.findByIdAndCustomerIdAndKindergardenId(customerId, kindergardenId, kindergardenGroupId)
        group.addChild(child)
        return childRepository.save(child)
    }
}