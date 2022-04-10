package com.almeida.henrique.gym_tracking.repositories

import com.almeida.henrique.gym_tracking.domain.Gym
import com.almeida.henrique.gym_tracking.domain.enum.TypeServiceGym
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface GymRepository : MongoRepository<Gym, String> {

    //@Query(value = "{typeServices:'?0'}", fields = "{'id':1,'name':1,'address':1,'openingHours':1,'phoneNumber':1}")
    fun findByTypeService(typeServiceGym: TypeServiceGym): List<Gym>
}