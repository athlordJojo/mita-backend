package com.jachouni.mitabackend.controllers

import com.jachouni.mitabackend.KindergardenGroupDto
import com.jachouni.mitabackend.entities.Groupbook
import com.jachouni.mitabackend.entities.KindergardenGroup
import com.jachouni.mitabackend.entities.repositories.CustomerRepository
import com.jachouni.mitabackend.entities.repositories.KindergardenGroupRepository
import com.jachouni.mitabackend.entities.repositories.KindergardenRepository
import com.jachouni.mitabackend.services.KindergardenGroupService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.persistence.EntityNotFoundException


@RestController
class KindergardenGroupController(@Autowired val kindergardenGroupService: KindergardenGroupService) {

//    @PostMapping(value = ["/customers/{customer-id}/kindergardens/{kindergarden-id}/kindergardengroup"])
//    @ResponseBody
//    fun createKindergardenGroup(@PathVariable("customer-id") customerId: UUID, @PathVariable("kindergarden-id") kindergardenId: UUID, @RequestBody dto: KindergardenGroupDto) {
//        if (kindergarden.isPresent) {
//            val k = kindergarden.get()
//            k.addKindergardenGroup(KindergardenGroup(name = dto.name, kindergarden = k, groupbook = Groupbook("Groubbook of group ${k.name}")))
//        } else {
//            throw EntityNotFoundException("Could not find kindergarden with id: $kindergardenId")
//        }
//    }

    @GetMapping(value = ["/customers/{customer-id}/kindergardens/{kindergarden-id}/kindergardengroup"])
    @ResponseBody
    fun getKindergardenGroups(@PathVariable("customer-id") customerId: UUID, @PathVariable("kindergarden-id") kindergardenId: UUID): ResponseEntity<List<KindergardenGroupDto>> {
        val kindergardens = kindergardenGroupService.getAllKindergarden(customerId, kindergardenId)
        val dtos = kindergardens.map { KindergardenGroupDto(id = it.id, name = it.name) }.toList()
        return ResponseEntity(dtos, HttpStatus.CREATED)
    }


}