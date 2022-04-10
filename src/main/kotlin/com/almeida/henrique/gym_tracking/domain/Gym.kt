package com.almeida.henrique.gym_tracking.domain

import com.almeida.henrique.gym_tracking.domain.enum.TypeServiceGym
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.io.Serializable

@Document("gym")
data class Gym(
    @Id var id: String,
    var name: String,
    var address: String,
    var openingHours: String,
    var phoneNumber: String,
    var typeServices: MutableList<TypeServiceGym>
) : Serializable
