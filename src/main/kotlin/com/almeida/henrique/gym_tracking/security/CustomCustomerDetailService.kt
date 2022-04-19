package com.almeida.henrique.gym_tracking.security

import com.almeida.henrique.gym_tracking.repositories.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service("customCustomerService")
class CustomCustomerDetailService : UserDetailsService {

    @Autowired
    private lateinit var customerRepository: CustomerRepository

    override fun loadUserByUsername(username: String?): UserDetails {
        val customer = username?.let { customerRepository.findById(it) } ?: throw UsernameNotFoundException(username)

        return User.withUsername(customer.get().id).password(customer.get().password).authorities("USER").build()
    }
}
