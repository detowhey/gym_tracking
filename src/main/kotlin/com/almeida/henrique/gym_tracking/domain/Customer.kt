package com.almeida.henrique.gym_tracking.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.mapping.FieldType
import java.io.Serializable


@Document(collection = "customer")
data class Customer(
    @Id
    @Field(targetType = FieldType.OBJECT_ID)
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val birthDay: String,
    val phoneNumber: String
) : Serializable {
    constructor(
        id: String, firstName: String, lastName: String, email: String, password: String, birthDay: String
    ) : this(id, firstName, lastName, email, password, birthDay, "")
}
