package com.almeida.henrique.gym_tracking.repositories

import com.almeida.henrique.gym_tracking.domain.Customer
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface CustomerRepository : MongoRepository<Customer, String> {

   /* @Query("{id:'?0'}")
    fun finById(id: String): Customer

    @Query("{id:'?0'}")
    fun findByFirstName(firstName: String): MutableList<Customer>

    fun findByLastName(lastName: String): MutableList<Customer>*/
}
