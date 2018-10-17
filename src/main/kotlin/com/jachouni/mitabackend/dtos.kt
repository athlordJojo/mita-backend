package com.jachouni.mitabackend

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate
import java.util.*

@DtoAnnotation
data class CustomerDto(var id: UUID? = null, var name: String)

@DtoAnnotation
data class KindergardenDto(var id: UUID? = null, var name: String)

@DtoAnnotation
data class KindergardenGroupDto(var id: UUID? = null, var name: String)

@DtoAnnotation
data class ChildDto(var id: UUID? = null, var firstname: String, var lastname: String,
                    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
                    var birthday: LocalDate)

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class DtoAnnotation