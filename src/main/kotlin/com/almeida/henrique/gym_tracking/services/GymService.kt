package com.almeida.henrique.gym_tracking.services

import com.almeida.henrique.gym_tracking.domain.Gym
import com.almeida.henrique.gym_tracking.dto.GymDTO
import com.almeida.henrique.gym_tracking.exception.DataBaseException
import com.almeida.henrique.gym_tracking.exception.ObjectNotFoundException
import com.almeida.henrique.gym_tracking.exception.ResourceNotFoundException
import com.almeida.henrique.gym_tracking.repositories.GymRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.data.domain.Sort
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service("gymService")
class GymService {

    @Autowired
    private lateinit var repository: GymRepository

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    fun findAll(): List<Gym> = repository.findAll(this.sortBy())

    fun findById(id: String): Gym {
        val optional: Optional<Gym> = repository.findById(id)
        return optional.orElseThrow { ObjectNotFoundException() }
    }

    fun findByName(name: String): List<Gym> {
        return repository.findByName(name, this.sortBy())
    }

    fun findByEmail(email: String): Gym = repository.findByEmail(email, this.sortBy("email"))

    fun findByOpeningHours(openingHours: String): List<Gym> {
        return repository.findByOpeningHours(openingHours, this.sortBy("openingHours"))
    }

    fun findByNameAndOpeningHours(name: String, openingHours: String): List<Gym> {
        return repository.findByNameAndOpeningHours(name, openingHours, this.sortBy("name", "openingHours"))
    }

    fun findByPriceBetween(iniPrice: Double, finalPrice: Double): List<Gym> {
        return repository.findByPriceBetween(iniPrice, finalPrice, this.sortBy("monthlyPayment"))
    }

    fun insert(gym: Gym): Gym {
        try {
            val id = this.findByEmail(gym.email).id
            val optional: Optional<Gym> = repository.findById(id)
            if (!optional.isEmpty) return repository.insert(gym) else throw ObjectNotFoundException()
        } catch (e: DataBaseException) {
            throw DataBaseException("Database not connect")
        }
    }

    fun delete(id: String) {
        try {
            this.findById(id)
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
        return Gym(
            gymDTO.id,
            gymDTO.email,
            passwordEncoder.encode(gymDTO.password),
            gymDTO.name,
            gymDTO.address,
            gymDTO.openingHours,
            gymDTO.phoneNumber,
            gymDTO.monthlyPayment
        )
    }

    private fun updateData(newGym: Gym, gym: Gym) {
        newGym.email = gym.email
        newGym.password = gym.password
        newGym.name = gym.name
        newGym.address = gym.address
        newGym.openingHours = gym.openingHours
        newGym.phoneNumber = gym.phoneNumber
        newGym.monthlyPayment = gym.monthlyPayment
    }

    private fun sortBy(firstProperty: String = "name", secondProperty: String): Sort {
        return Sort.by(Sort.Direction.ASC, firstProperty, secondProperty)
    }

    private fun sortBy(firstProperty: String = "name"): Sort = Sort.by(Sort.Direction.ASC, firstProperty)
}
