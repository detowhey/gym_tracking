package com.almeida.henrique.gym_tracking.repositories

import com.almeida.henrique.gym_tracking.domain.Customer
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository : MongoRepository<Customer, String> {

    @Query("{'firstName':{\$regex: ?0, \$options: 'i'}}")
    fun findByFirstNameRegex(name: String): List<Customer>




}
