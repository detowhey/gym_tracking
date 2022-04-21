package com.almeida.henrique.gym_tracking.services

import com.almeida.henrique.gym_tracking.domain.Customer
import com.almeida.henrique.gym_tracking.dto.CustomerDTO
import com.almeida.henrique.gym_tracking.repositories.CustomerRepository
import com.almeida.henrique.gym_tracking.services.exception.DataBaseException
import com.almeida.henrique.gym_tracking.services.exception.ObjectNotFoundException
import com.almeida.henrique.gym_tracking.services.exception.ResourceNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.data.domain.Sort
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service("customerService")
class CustomerService {

    @Autowired
    private lateinit var repository: CustomerRepository

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    fun findAll(): List<Customer> = repository.findAll(this.sortBy(secondProperty = "lastName"))

    fun findById(id: String): Customer {
        val optional: Optional<Customer> = repository.findById(id)
        return optional.orElseThrow { ObjectNotFoundException() }
    }

    fun findByFirstNameRegex(firstName: String?): List<Customer> {
        return repository.findByFirstNameRegex(firstName, this.sortBy())
    }

    fun findByFullNameRegex(firstName: String?, lastName: String?): List<Customer> {
        return repository.findByFullNameRegex(firstName, lastName, this.sortBy(secondProperty = "lastName"))
    }

    fun insert(customer: Customer): Customer {
        try {
            return repository.insert(customer)
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

    fun update(newCustomerData: Customer): Customer {
        val customerData = this.findById(newCustomerData.id)
        updateData(customerData, newCustomerData)
        return repository.save(customerData)
    }

    fun fromDTO(customerDTO: CustomerDTO): Customer {
        return Customer(
            customerDTO.id, customerDTO.firstName, customerDTO.lastName, customerDTO.email,
            passwordEncoder.encode(customerDTO.password), customerDTO.birthDay, customerDTO.phoneNumber
        )
    }

    private fun updateData(newCustomer: Customer, customer: Customer) {
        newCustomer.firstName = customer.firstName
        newCustomer.lastName = customer.lastName
        newCustomer.email = customer.email
        newCustomer.password = passwordEncoder.encode(customer.password)
        newCustomer.birthDay = customer.birthDay
        newCustomer.phoneNumber = customer.phoneNumber
    }

    private fun sortBy(firstProperty: String = "firstName", secondProperty: String): Sort {
        return Sort.by(Sort.Direction.ASC, firstProperty, secondProperty)
    }

    private fun sortBy(firstProperty: String = "firstName"): Sort = Sort.by(Sort.Direction.ASC, firstProperty)
}
