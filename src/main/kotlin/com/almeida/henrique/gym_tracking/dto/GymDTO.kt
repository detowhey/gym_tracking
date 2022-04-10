package com.almeida.henrique.gym_tracking.dto

import com.almeida.henrique.gym_tracking.domain.Gym
import com.almeida.henrique.gym_tracking.domain.enum.TypeServiceGym
import java.io.Serializable

class GymDTO(gym: Gym) : Serializable {

    val id: String = gym.id
    val name: String = gym.name
    val address: String = gym.address
    val openingHours: String = gym.openingHours
    val phoneNumber: String = gym.phoneNumber
    val typeServices: MutableList<TypeServiceGym> = gym.typeServices

    companion object {
        private const val serialVersionUID = 1L
    }
}
