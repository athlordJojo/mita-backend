package com.jachouni.mitabackend.controllers

import com.jachouni.mitabackend.GroupbookDto
import com.jachouni.mitabackend.services.GroupbookService
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class GroupbookController(@Autowired val groupbookService: GroupbookService) {


    @GetMapping(value = ["/customers/{customer-id}/kindergardens/{kindergarden-id}/kindergardengroups/{group-id}/groupbook"])
    @ResponseBody
    fun getGroupbook(@PathVariable("customer-id") customerId: UUID,
                     @PathVariable("kindergarden-id") kindergardenId: UUID,
                     @PathVariable("group-id") kindergardenGroupId: UUID): ResponseEntity<GroupbookDto> {
        val modelMapper = ModelMapper()
        val dto = modelMapper.map(groupbookService.getGroupbook(customerId, kindergardenId, kindergardenGroupId), GroupbookDto::class.java)
        return ResponseEntity.ok(dto)
    }

}