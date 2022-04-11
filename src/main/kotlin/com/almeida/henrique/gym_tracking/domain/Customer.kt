package com.almeida.henrique.gym_tracking.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
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
    var id: String,
    var firstName: String,
    var lastName: String,
    var email: String,
    var password: String,
    var birthDay: String,
    var phoneNumber: String
) : Serializable
