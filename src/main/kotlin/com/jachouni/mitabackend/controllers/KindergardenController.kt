package com.jachouni.mitabackend.controllers

import com.jachouni.mitabackend.KindergardenDto
import com.jachouni.mitabackend.entities.Kindergarden
import com.jachouni.mitabackend.services.KindergardenService
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class KindergardenController(@Autowired
                             var kindergardenService: KindergardenService
) {

    @PostMapping(value = ["/customers/{customer-id}/kindergardens"])
    @ResponseBody
    fun createKindergarden(@PathVariable("customer-id") customerId: UUID, @RequestBody dto: KindergardenDto): ResponseEntity<Kindergarden> {
        val modelMapper = ModelMapper()
        val kindergarden = modelMapper.map(dto, Kindergarden::class.java)
        kindergardenService.createKindergarden(customerId, kindergarden)
        return ResponseEntity(kindergarden, HttpStatus.CREATED)
    }


    @GetMapping(value = ["/customers/{customer-id}/kindergardens"])
    @ResponseBody
    fun getKindergardens(@PathVariable("customer-id") customerId: UUID): ResponseEntity<List<KindergardenDto>> {
        val modelMapper = ModelMapper()
        val kinderGardens = kindergardenService.getAllKinderGardens(customerId)
        val dto = kinderGardens.map { kindergarden -> modelMapper.map(kindergarden, KindergardenDto::class.java) }

        return ResponseEntity.ok(dto)
    }


}