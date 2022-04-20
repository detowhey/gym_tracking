package com.almeida.henrique.gym_tracking.repositories

import com.almeida.henrique.gym_tracking.domain.Customer
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository : MongoRepository<Customer, String> {

    @Query("{'firstName':{\$regex: ?0, \$options: 'i'}}")
    fun findByFirstNameRegex(@Param("firstName") name: String?): List<Customer>

    @Query("{'firstName': {\$regex: ?0, \$options: 'i'}, 'lastName': {\$regex: ?1, \$options: 'i'}}")
    fun findByFullNameRegex(@Param("firstName") firstName: String?, @Param("lastName") lastName: String?): List<Customer>

}
