package com.almeida.henrique.gym_tracking.services

import com.almeida.henrique.gym_tracking.entities.Gym
import com.almeida.henrique.gym_tracking.entities.enum.TypeServiceGym
import com.almeida.henrique.gym_tracking.repositories.GymRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GymService {

    @Autowired
    private lateinit var repository: GymRepository

    fun findAll(): List<Gym> = repository.findAll()

    fun findById(id: String): Gym {
        val optional = repository.findById(id)

        if (optional.isPresent)
            return optional.get()
        throw IllegalArgumentException()
    }

    fun findByTypeService(typeServiceGym: TypeServiceGym): List<Gym> {
        val optional = repository.findByTypeService(typeServiceGym)

        if (optional.isNotEmpty())
            return optional
        throw IndexOutOfBoundsException()
    }
}
