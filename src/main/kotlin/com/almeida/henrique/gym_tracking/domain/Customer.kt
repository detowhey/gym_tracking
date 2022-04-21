package com.almeida.henrique.gym_tracking.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.mapping.FieldType
import java.io.Serializable
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Document(collection = "customer")
data class Customer(
    @Id
    @Field(targetType = FieldType.OBJECT_ID)
    var id: String,
    @NotBlank
    @Size(max = 100)
    var firstName: String,
    @NotBlank
    @Size(max = 100)
    var lastName: String,
    @NotBlank
    @Size(max = 100)
    var email: String,
    @NotBlank
    @Size(max = 100)
    var password: String,
    @NotBlank
    @Size(max = 100)
    var birthDay: String,
    var phoneNumber: String
) : Serializable
