package com.jachouni.mitabackend.controllers

import com.jachouni.mitabackend.KindergardenGroupDto
import com.jachouni.mitabackend.entities.KindergardenGroup
import com.jachouni.mitabackend.services.KindergardenGroupService
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
class KindergardenGroupController(@Autowired val kindergardenGroupService: KindergardenGroupService) {

    @PostMapping(value = ["/customers/{customer-id}/kindergardens/{kindergarden-id}/kindergardengroups"])
    @ResponseBody
    fun createKindergardenGroup(@PathVariable("customer-id") customerId: UUID,
                                @PathVariable("kindergarden-id") kindergardenId: UUID,
                                @RequestBody dto: KindergardenGroupDto): ResponseEntity<KindergardenGroupDto> {
        val modelMapper = ModelMapper()
        val kindergardenGroup = modelMapper.map(dto, KindergardenGroup::class.java)
        val updatedKindergardenGroup = kindergardenGroupService.createKindergardenGroup(customerId, kindergardenId, kindergardenGroup)

        return ResponseEntity(modelMapper.map(updatedKindergardenGroup, KindergardenGroupDto::class.java), HttpStatus.OK)
    }

    @GetMapping(value = ["/customers/{customer-id}/kindergardens/{kindergarden-id}/kindergardengroups"])
    @ResponseBody
    fun getKindergardenGroups(@PathVariable("customer-id") customerId: UUID,
                              @PathVariable("kindergarden-id") kindergardenId: UUID): ResponseEntity<List<KindergardenGroupDto>> {
        val kindergardenGroups = kindergardenGroupService.getAllKindergardenGroups(customerId, kindergardenId)
        val dtos = kindergardenGroups.map { KindergardenGroupDto(id = it.id, name = it.name) }.toList()
        return ResponseEntity(dtos, HttpStatus.OK)
    }

    @GetMapping(value = ["/customers/{customer-id}/kindergardens/{kindergarden-id}/kindergardengroups/{groupId}"])
    @ResponseBody
    fun getKindergardenGroup(@PathVariable("customer-id") customerId: UUID,
                             @PathVariable("kindergarden-id") kindergardenId: UUID,
                             @PathVariable("groupId") id: UUID): ResponseEntity<KindergardenGroupDto> {
        val kindergardenGroup = kindergardenGroupService.getKindergardenGroup(customerId, kindergardenId, id)
        val modelMapper = ModelMapper()
        val dto = modelMapper.map(kindergardenGroup, KindergardenGroupDto::class.java)
        return ResponseEntity(dto, HttpStatus.OK)
    }
}