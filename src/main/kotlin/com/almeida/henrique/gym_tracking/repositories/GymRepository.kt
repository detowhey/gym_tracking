package com.almeida.henrique.gym_tracking.repositories

import com.almeida.henrique.gym_tracking.domain.Gym
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface GymRepository : MongoRepository<Gym, String> {

}