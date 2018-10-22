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
data class GroupbookDto(var id: UUID? = null, var name: String)

@DtoAnnotation
data class ChildDto(var id: UUID? = null, var firstname: String, var lastname: String, var sex:String,
                    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
                    var birthday: LocalDate)

@DtoAnnotation
data class DayEntryDto(var id: UUID? = null, var dayDate:String, var arrivedAt:String?, var leftAt:String?)


@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class DtoAnnotation