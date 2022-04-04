package com.almeida.henrique.gym_tracking.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("customer")
data class Customer(
    @Id val id: String,
    val firstName: String,
    val lastName: String,
    val address: String,
    val birthDay: String,
    val phoneNumber: String
) {
    constructor(
        id: String, firstName: String, lastName: String, address: String, birthDay: String
    ) : this(id, firstName, lastName, address, birthDay, "")
}
