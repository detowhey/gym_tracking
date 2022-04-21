package com.almeida.henrique.gym_tracking.domain

import io.swagger.annotations.ApiModelProperty
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
    @ApiModelProperty(
        notes = "Id of customer",
        example = "4e4eeb3948198f4fdf3bfbb46a67aaa077e5f82a",
        required = true,
        dataType = "string",
        position = 1
    )
    var id: String,
    @NotBlank
    @Size(max = 100)
    @ApiModelProperty(
        notes = "First name of customer",
        example = "Paul",
        required = true,
        dataType = "string",
        position = 2
    )
    var firstName: String,
    @NotBlank
    @Size(max = 100)
    @ApiModelProperty(
        notes = "Last name of customer",
        example = "Santos",
        required = true,
        dataType = "string",
        position = 3
    )
    var lastName: String,
    @NotBlank
    @Size(max = 100)
    @ApiModelProperty(
        notes = "E-mail of customer",
        example = "example@email.com",
        required = true,
        dataType = "string",
        position = 4
    )
    var email: String,
    @NotBlank
    @Size(max = 100)
    @ApiModelProperty(
        notes = "Password of customer",
        example = "###############",
        required = true,
        dataType = "string",
        position = 5
    )
    var password: String,
    @NotBlank
    @Size(max = 100)
    @ApiModelProperty(
        notes = "Birthday of customer",
        example = "dd/mm/yyyy",
        required = true,
        dataType = "string",
        position = 6
    )
    var birthDay: String,
    @ApiModelProperty(
        notes = "Phone number of customer",
        example = "(00)987654321",
        required = false,
        dataType = "string",
        position = 7
    )
    var phoneNumber: String
) : Serializable
