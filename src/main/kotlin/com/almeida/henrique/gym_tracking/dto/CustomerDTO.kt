package com.almeida.henrique.gym_tracking.dto

import com.almeida.henrique.gym_tracking.domain.Customer
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

class CustomerDTO(customer: Customer) : Serializable {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    val id: String = customer.id
    val firstName: String = customer.firstName
    val lastName: String = customer.lastName
    val email: String = customer.email

    @JsonIgnore
    val password: String = customer.password
    val birthDay: String = customer.birthDay
    val phoneNumber: String = customer.phoneNumber

    companion object {
        private const val serialVersionUID = 1L
    }
}