package com.almeida.henrique.gym_tracking.resource

import com.almeida.henrique.gym_tracking.domain.Customer
import com.almeida.henrique.gym_tracking.dto.CustomerDTO
import com.almeida.henrique.gym_tracking.services.CustomerService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI
import kotlin.streams.toList

@RestController
@RequestMapping("/api/v1")
@Api(value = "Customer", description = "Customer related operations")
class CustomerResource {

    companion object {
        private const val RESOURCE = "/customer"
        private const val APPLICATION_JSON = " application/json"
    }

    @Autowired
    private lateinit var service: CustomerService

    @ApiOperation("Return just one customer")
    @GetMapping("$RESOURCE/{id}", produces = [APPLICATION_JSON])
    fun findById(@PathVariable id: String): ResponseEntity<CustomerDTO> {
        return ResponseEntity.ok().body(CustomerDTO(service.findById(id)))
    }

    @ApiOperation("Returns a list of customers")
    @GetMapping(RESOURCE, produces = [APPLICATION_JSON])
    fun findByFirstNameOrFullName(
        @ApiParam(value = "First name of customer", example = "eder")
        @RequestParam(required = false) firstName: String?,
        @ApiParam(value = "Last name of customer", example = "jofre")
        @RequestParam(required = false) lastName: String?
    ): ResponseEntity<List<CustomerDTO>> {
        return if ((firstName.isNullOrEmpty() || firstName.isNullOrBlank()) && (lastName.isNullOrEmpty() || lastName.isNullOrBlank()))
            ResponseEntity.ok().body(this.listCustomerToListDto(service.findAll()))
        else if (lastName.isNullOrEmpty() || lastName.isNullOrBlank())
            ResponseEntity.ok().body(this.listCustomerToListDto(service.findByFirstNameRegex(firstName)))
        else
            ResponseEntity.ok().body(this.listCustomerToListDto(service.findByFullNameRegex(firstName, lastName)))
    }

    @ApiOperation("Register a customer")
    @PostMapping(RESOURCE, produces = [APPLICATION_JSON], consumes = [APPLICATION_JSON])
    fun insertCustomer(@RequestBody customerDTO: CustomerDTO): ResponseEntity<CustomerDTO> {
        var customer = service.fromDTO(customerDTO)
        customer.id = ObjectId.get().toString()
        customer = service.insert(customer)
        val uri: URI = ServletUriComponentsBuilder
            .fromCurrentRequest().path("{/id}").buildAndExpand(customer.id).toUri()
        return ResponseEntity.created(uri).body(customerDTO)
    }

    @ApiOperation("Delete customer record")
    @DeleteMapping("$RESOURCE/{id}", consumes = [APPLICATION_JSON])
    fun deleteCustomer(@PathVariable id: String): ResponseEntity<String> {
        service.delete(id)
        return ResponseEntity.ok().body("Customer successfully deleted: id $id")
    }

    @ApiOperation("Update customer record")
    @PutMapping("$RESOURCE/{id}", produces = [APPLICATION_JSON], consumes = [APPLICATION_JSON])
    fun updateCustomer(@RequestBody customerDTO: CustomerDTO, @PathVariable id: String): ResponseEntity<CustomerDTO> {
        val customer = service.fromDTO(customerDTO)
        customer.id = id
        service.update(customer)
        return ResponseEntity.ok().body(customerDTO)
    }

    private fun listCustomerToListDto(listCustomer: List<Customer>): List<CustomerDTO> {
        return listCustomer.stream().map { CustomerDTO(it) }.toList()
    }
}
