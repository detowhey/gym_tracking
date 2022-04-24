package com.almeida.henrique.gym_tracking.dto

import com.almeida.henrique.gym_tracking.domain.Gym
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.io.Serializable

@ApiModel(value = "Gym")
class GymDTO(gym: Gym) : Serializable {

    @ApiModelProperty(position = 0, example = "4e4eeb3948198f4fdf3bfbb46a67aaa077e5f82a", required = true)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    val id: String = gym.id

    @ApiModelProperty(position = 1, example = "gym@email.com", required = true)
    val email: String = gym.email

    @ApiModelProperty(required = true, hidden = true)
    @JsonIgnore
    val password: String = gym.password

    @ApiModelProperty(position = 2, example = "Temple Gym", required = true)
    val name: String = gym.name

    @ApiModelProperty(position = 3, example = "Av. Espírito Santo - Bela Aurora, Cariacica - ES", required = true)
    val address: String = gym.address

    @ApiModelProperty(position = 4, example = "08:00-23:00", required = true)
    val openingHours: String = gym.openingHours

    @ApiModelProperty(position = 5, example = "(00)123456789", required = true)
    val phoneNumber: String = gym.phoneNumber

    @ApiModelProperty(position = 6, example = "150.00", required = true)
    val monthlyPayment: Double = gym.monthlyPayment

    companion object {
        private const val serialVersionUID = 1L
    }
}
