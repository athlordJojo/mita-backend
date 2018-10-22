package com.jachouni.mitabackend.services

import com.jachouni.mitabackend.entities.Groupbook
import com.jachouni.mitabackend.entities.repositories.GroupbookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class GroupbookService(@Autowired val groupbookRepository: GroupbookRepository) {

    fun getGroupbook(customerId: UUID, kindergardenId: UUID, group: UUID): Groupbook {
        return groupbookRepository.getGroupbook(customerId, kindergardenId, group)
    }
}