package com.jachouni.mitabackend.controllers

import com.jachouni.mitabackend.ChildDto
import com.jachouni.mitabackend.services.ChildService
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class ChildController(@Autowired val childService: ChildService) {

    @GetMapping(value = ["/customers/{customer-id}/kindergardens/{kindergarden-id}/kindergardengroup/{groupId}/childs"])
    @ResponseBody
    fun getChilds(@PathVariable("customer-id") customerId: UUID,
                  @PathVariable("kindergarden-id") kindergardenId: UUID,
                  @PathVariable("groupId") kindergardenGroupId: UUID): ResponseEntity<List<ChildDto>> {
        val childs = childService.getChildsOfGroup(customerId, kindergardenId, kindergardenGroupId)
        val modelMapper = ModelMapper()
        val dtos = childs.map { c -> modelMapper.map(c, ChildDto::class.java)}.toList()
        return ResponseEntity(dtos, HttpStatus.OK)
    }

}