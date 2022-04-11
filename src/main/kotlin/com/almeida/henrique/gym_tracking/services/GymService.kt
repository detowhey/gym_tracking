package com.almeida.henrique.gym_tracking.services

import com.almeida.henrique.gym_tracking.domain.Gym
import com.almeida.henrique.gym_tracking.dto.GymDTO
import com.almeida.henrique.gym_tracking.repositories.GymRepository
import com.almeida.henrique.gym_tracking.services.exception.ObjectNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class GymService {

    @Autowired
    private lateinit var repository: GymRepository

    fun findAll(): List<Gym> = repository.findAll()

    fun findById(id: String): Gym {
        val optional: Optional<Gym> = repository.findById(id)
        return optional.orElseThrow { ObjectNotFoundException("Object not found") }
    }

    fun insert(gym: Gym): Gym = repository.insert(gym)

    fun delete(id: String) = repository.deleteById(id)

    fun update(gym: Gym): Gym {
        val newGymData = this.findById(gym.id)
        updateData(newGymData, gym)
        return repository.save(newGymData)
    }

    fun fromDTO(gymDTO: GymDTO): Gym {
        return Gym(gymDTO.id, gymDTO.name, gymDTO.address, gymDTO.openingHours, gymDTO.phoneNumber)
    }

    private fun updateData(newGym: Gym, gym: Gym) {
        newGym.name = gym.name
        newGym.phoneNumber = gym.phoneNumber
        newGym.openingHours = gym.openingHours
        newGym.address = gym.address
    }

}
