package com.almeida.henrique.gym_tracking.dto

import com.almeida.henrique.gym_tracking.domain.Customer
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.io.Serializable

@ApiModel(value = "Customer")
class CustomerDTO(customer: Customer) : Serializable {

    @ApiModelProperty(position = 0, example = "4e4eeb3948198f4fdf3bfbb46a67aaa077e5f82a", required = true)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    val id: String = customer.id

    @ApiModelProperty(position = 1, example = "eder", required = true)
    val firstName: String = customer.firstName

    @ApiModelProperty(position = 2, example = "jofre", required = true)
    val lastName: String = customer.lastName

    @ApiModelProperty(position = 3, example = "example@email.com", required = true)
    val email: String = customer.email

    @ApiModelProperty(required = true, hidden = true)
    @JsonIgnore
    val password: String = customer.password

    @ApiModelProperty(position = 4, example = "dd/mm/yyyy", required = true)
    val birthDay: String = customer.birthDay

    @ApiModelProperty(position = 5, example = "(00)123456789", required = false)
    val phoneNumber: String = customer.phoneNumber

    companion object {
        private const val serialVersionUID = 1L
    }
}
