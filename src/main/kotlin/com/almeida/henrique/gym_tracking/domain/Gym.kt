package com.almeida.henrique.gym_tracking.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.mapping.FieldType
import java.io.Serializable

@Document(collection = "gym")
data class Gym(
    @Id
    @Field(targetType = FieldType.OBJECT_ID)
    var id: String,
    var email: String,
    var password: String,
    var name: String,
    var address: String,
    var openingHours: String,
    var phoneNumber: String,
    var monthlyPayment: Double
) : Serializable
