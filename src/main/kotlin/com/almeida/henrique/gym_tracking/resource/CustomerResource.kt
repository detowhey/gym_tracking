package com.almeida.henrique.gym_tracking.resource

import com.almeida.henrique.gym_tracking.domain.Customer
import com.almeida.henrique.gym_tracking.dto.CustomerDTO
import com.almeida.henrique.gym_tracking.services.CustomerService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI
import kotlin.streams.toList

@RestController
@RequestMapping("/api/v1")
@Api(value = "/customer", tags = ["Customer"])
class CustomerResource {

    companion object {
        private const val RESOURCE = "/customer"
        private const val APPLICATION_JSON = " application/json"
    }

    @Autowired
    private lateinit var service: CustomerService

    @GetMapping("$RESOURCE/{id}", produces = [APPLICATION_JSON])
    @ApiOperation("Return just one customer")
    fun findById(@PathVariable id: String): ResponseEntity<CustomerDTO> {
        return ResponseEntity.ok().body(CustomerDTO(service.findById(id)))
    }


    @GetMapping(RESOURCE, produces = [APPLICATION_JSON])
    @ApiOperation(value = "Returns a list of customers")
    @ApiResponses(
        ApiResponse(code = 200, message = "Found customers", response = CustomerDTO::class, responseContainer = "List")
    )
    fun findByFirstNameOrFullName(
        @ApiParam(value = "First name of customer", example = "eder", required = false)
        @RequestParam(required = false) firstname: String?,
        @ApiParam(value = "Last name of customer", example = "jofre", required = false)
        @RequestParam(required = false) lastname: String?
    ): ResponseEntity<List<CustomerDTO>> {
        return if ((firstname.isNullOrEmpty() || firstname.isBlank()) && (lastname.isNullOrEmpty() || lastname.isBlank()))
            ResponseEntity.ok().body(this.listCustomerToListDto(service.findAll()))
        else if (lastname.isNullOrEmpty() || lastname.isBlank())
            ResponseEntity.ok().body(this.listCustomerToListDto(service.findByFirstNameRegex(firstname)))
        else
            ResponseEntity.ok().body(this.listCustomerToListDto(service.findByFullNameRegex(firstname, lastname)))
    }

    @ApiOperation("Register a customer", code = 201, response = CustomerDTO::class)
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
    @DeleteMapping("$RESOURCE/{id}", produces = [APPLICATION_JSON])
    fun deleteCustomer(@PathVariable id: String): ResponseEntity<Map<String, Any>> {
        service.delete(id)
        return ResponseEntity.ok()
            .body(
                mapOf(
                    "id" to id,
                    "message" to "Customer successfully deleted",
                    "status" to 200
                )
            )
    }

    @ApiOperation("Update customer record")
    @PutMapping("$RESOURCE/{id}", consumes = [APPLICATION_JSON], produces = [APPLICATION_JSON])
    fun updateCustomer(
        @RequestBody customerDTO: CustomerDTO,
        @PathVariable id: String
    ): ResponseEntity<Map<String, Any>> {
        val customer = service.fromDTO(customerDTO)
        customer.id = id
        service.update(customer)

        return ResponseEntity.ok().body(
            mapOf(
                "id" to id,
                "message" to "Customer successfully updated",
                "status" to 201
            )
        )
    }

    private fun listCustomerToListDto(listCustomer: List<Customer>): List<CustomerDTO> {
        return listCustomer.stream().map { CustomerDTO(it) }.toList()
    }
}
