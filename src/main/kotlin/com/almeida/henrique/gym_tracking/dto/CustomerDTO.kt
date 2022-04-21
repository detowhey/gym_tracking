package com.almeida.henrique.gym_tracking.dto

import com.almeida.henrique.gym_tracking.domain.Customer
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.io.Serializable
@ApiModel(value = "Customer")
class CustomerDTO( customer: Customer) : Serializable {
    @ApiModelProperty(
        position = 0,
        example = "4e4eeb3948198f4fdf3bfbb46a67aaa077e5f82a"

    )
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    val id: String = customer.id

    @ApiModelProperty(
        notes = "Id of customer",
        example = "4e4eeb3948198f4fdf3bfbb46a67aaa077e5f82a",
        required = true,
        dataType = "string",
        position = 1
    )
    val firstName: String = customer.firstName

    @ApiModelProperty(
        notes = "Id of customer",
        example = "4e4eeb3948198f4fdf3bfbb46a67aaa077e5f82a",
        required = true,
        dataType = "string",
        position = 2
    )
    val lastName: String = customer.lastName

    @ApiModelProperty(
        notes = "Id of customer",
        example = "4e4eeb3948198f4fdf3bfbb46a67aaa077e5f82a",
        required = true,
        dataType = "string",
        position = 3
    )
    val email: String = customer.email

    @JsonIgnore
    @ApiModelProperty(
        notes = "Id of customer",
        example = "4e4eeb3948198f4fdf3bfbb46a67aaa077e5f82a",
        required = true,
        dataType = "string",
        position = 4
    )
    val password: String = customer.password

    @ApiModelProperty(
        notes = "Id of customer",
        example = "4e4eeb3948198f4fdf3bfbb46a67aaa077e5f82a",
        required = true,
        dataType = "string",
        position = 5
    )
    val birthDay: String = customer.birthDay

    @ApiModelProperty(
        notes = "Id of customer",
        example = "4e4eeb3948198f4fdf3bfbb46a67aaa077e5f82a",
        required = true,
        dataType = "string",
        position = 6
    )
    val phoneNumber: String = customer.phoneNumber

    companion object {
        private const val serialVersionUID = 1L
    }
}