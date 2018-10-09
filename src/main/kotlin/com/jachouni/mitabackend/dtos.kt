package com.jachouni.mitabackend

import java.util.*

data class CustomerDto(val id: UUID? = null, val name: String, var kindergarden: MutableList<KindergardenDto> = mutableListOf())

data class KindergardenDto(val id: UUID? = null, val name: String)

data class KindergardenGroupDto(val name: String)