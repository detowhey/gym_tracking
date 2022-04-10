package com.almeida.henrique.gym_tracking.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.io.Serializable


@Document("customer")
data class Customer(
    @Id val id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val address: String,
    val birthDay: String,
    val phoneNumber: String
) : Serializable {
    constructor(
        id: String, firstName: String, lastName: String, email: String, address: String, birthDay: String
    ) : this(id, firstName, lastName, email, address, birthDay, "")
}
