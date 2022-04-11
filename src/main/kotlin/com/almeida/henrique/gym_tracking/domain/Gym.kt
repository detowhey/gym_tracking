package com.almeida.henrique.gym_tracking.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.mapping.FieldType
import java.io.Serializable
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size
import kotlin.math.max

@Document(collection = "gym")
data class Gym(
    @Id
    @Field(targetType = FieldType.OBJECT_ID)
    var id: String,
    @NotBlank
    @Size(max = 100)
    var name: String,
    @NotBlank
    @Size(max = 100)
    var address: String,
    @NotBlank
    @Size(max = 100)
    var openingHours: String,
    @NotBlank
    @Size(max = 100)
    var phoneNumber: String
) : Serializable
