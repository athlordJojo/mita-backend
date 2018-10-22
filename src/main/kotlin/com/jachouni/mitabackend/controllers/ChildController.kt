package com.jachouni.mitabackend.controllers

import com.jachouni.mitabackend.ChildDto
import com.jachouni.mitabackend.DayEntryDto
import com.jachouni.mitabackend.entities.Child
import com.jachouni.mitabackend.services.ChildService
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class ChildController(@Autowired val childService: ChildService) {

    @GetMapping(value = ["/customers/{customer-id}/kindergardens/{kindergarden-id}/kindergardengroups/{group-id}/childs"])
    @ResponseBody
    fun getChilds(@PathVariable("customer-id") customerId: UUID,
                  @PathVariable("kindergarden-id") kindergardenId: UUID,
                  @PathVariable("group-id") kindergardenGroupId: UUID): ResponseEntity<List<ChildDto>> {
        val childs = childService.getChildsOfGroup(customerId, kindergardenId, kindergardenGroupId)
        val modelMapper = ModelMapper()
        val dtos = childs.map { c -> modelMapper.map(c, ChildDto::class.java) }.toList()
        return ResponseEntity.ok(dtos)
    }

    @PostMapping(value = ["/customers/{customer-id}/kindergardens/{kindergarden-id}/kindergardengroups/{group-id}/childs"])
    @ResponseBody
    fun createChild(@PathVariable("customer-id") customerId: UUID,
                    @PathVariable("kindergarden-id") kindergardenId: UUID,
                    @PathVariable("group-id") kindergardenGroupId: UUID,
                    @RequestBody childDto: ChildDto
    ): ResponseEntity<ChildDto> {
        val modelMapper = ModelMapper()
        val child = modelMapper.map(childDto, Child::class.java)
        childService.createChild(customerId, kindergardenId, kindergardenGroupId, child)
        val dto = modelMapper.map(child, ChildDto::class.java)
        return ResponseEntity(dto, HttpStatus.CREATED)
    }


    @GetMapping(value = ["/customers/{customer-id}/kindergardens/{kindergarden-id}/kindergardengroups/{group-id}/childs/{child-id}"])
    @ResponseBody
    fun getChild(@PathVariable("customer-id") customerId: UUID,
                 @PathVariable("kindergarden-id") kindergardenId: UUID,
                 @PathVariable("group-id") kindergardenGroupId: UUID,
                 @PathVariable("child-id") childId: UUID
    ): ResponseEntity<ChildDto> {
        val modelMapper = ModelMapper()
        val child = childService.getChildOfGroup(customerId, kindergardenId, kindergardenGroupId, childId)
        val dto = modelMapper.map(child, ChildDto::class.java)
        return ResponseEntity.ok(dto)
    }

    @GetMapping(value = ["/customers/{customer-id}/kindergardens/{kindergarden-id}/kindergardengroups/{group-id}/childs/{child-id}/day-entries"])
    @ResponseBody
    fun getEntriesOfChild(@PathVariable("customer-id") customerId: UUID,
                          @PathVariable("kindergarden-id") kindergardenId: UUID,
                          @PathVariable("group-id") kindergardenGroupId: UUID,
                          @PathVariable("child-id") childId: UUID
    ): ResponseEntity<List<DayEntryDto>> {
        val modelMapper = ModelMapper()
        val entriesOfChild = childService.getEntriesOfChild(customerId, kindergardenId, kindergardenGroupId, childId)
        val dtos = entriesOfChild.map { it -> modelMapper.map(it, DayEntryDto::class.java) }.toList()
        return ResponseEntity.ok(dtos)
    }
}