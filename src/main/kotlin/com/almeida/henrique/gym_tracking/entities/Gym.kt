package com.almeida.henrique.gym_tracking.entities

import com.almeida.henrique.gym_tracking.entities.enum.TypeServiceGym
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("gym")
data class Gym(
    @Id val id: String,
    val name: String,
    val address: String,
    val openingHours: String,
    val phoneNumber: String,
    val typeServices: MutableList<TypeServiceGym>
)
