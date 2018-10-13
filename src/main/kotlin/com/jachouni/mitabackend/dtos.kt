package com.jachouni.mitabackend

import java.util.*

@DtoAnnotation
data class CustomerDto (var id: UUID? = null, var name: String)

@DtoAnnotation
data class KindergardenDto(var id: UUID? = null, var name: String)

@DtoAnnotation
data class KindergardenGroupDto(var id: UUID? = null, var name: String)

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class DtoAnnotation