package com.almeida.henrique.gym_tracking.repositories

import com.almeida.henrique.gym_tracking.domain.Gym
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface GymRepository : MongoRepository<Gym, String> {

    @Query("{'name':{\$regex: ?0, \$options: 'i' }}")
    fun findByName(@Param("name") name: String?, sort: Sort): List<Gym>

    @Query("{'openingHours':{\$regex: ?0, \$options: 'i' }}")
    fun findByOpeningHours(@Param("openingHours") name: String?, sort: Sort): List<Gym>

    @Query("{'name': {\$regex: ?0, \$options: 'i'}, 'openingHours': {\$regex: ?1, \$options: 'i' }}")
    fun findByNameAndOpeningHours(
        @Param("name") name: String?,
        @Param("openingHours") openingHours: String?,
        sort: Sort
    ): List<Gym>

    @Query("{'email': ?0 }")
    fun findByEmail(@Param("email") email: String?): List<Gym>

    @Query("{'monthlyPayment' :{ \$gte: ?0, \$lte: ?1 } }")
    fun findByPriceBetween(iniPrice: Double, finalPrice: Double, sort: Sort): List<Gym>
}