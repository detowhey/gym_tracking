package com.almeida.henrique.gym_tracking.services

import com.almeida.henrique.gym_tracking.domain.Gym
import com.almeida.henrique.gym_tracking.dto.GymDTO
import com.almeida.henrique.gym_tracking.repositories.GymRepository
import com.almeida.henrique.gym_tracking.services.exception.ObjectNotFoundException
import com.almeida.henrique.gym_tracking.services.exception.ResourceNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service
import java.util.*

@Service
class GymService {

    @Autowired
    private lateinit var repository: GymRepository

    fun findAll(): List<Gym> = repository.findAll()

    fun findById(id: String): Gym {
        val optional: Optional<Gym> = repository.findById(id)
        return optional.orElseThrow { ObjectNotFoundException() }
    }

    fun insert(gym: Gym): Gym = repository.insert(gym)

    fun delete(id: String) {
        try {
            repository.deleteById(id)
        } catch (e: EmptyResultDataAccessException) {
            throw ResourceNotFoundException(id)
        }
    }

    fun update(newGymData: Gym): Gym {
        val gymData = this.findById(newGymData.id)
        updateData(gymData, newGymData)
        return repository.save(gymData)
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
