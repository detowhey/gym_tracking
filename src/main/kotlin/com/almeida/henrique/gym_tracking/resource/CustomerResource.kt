package com.almeida.henrique.gym_tracking.resource

import com.almeida.henrique.gym_tracking.domain.Customer
import com.almeida.henrique.gym_tracking.dto.CustomerDTO
import com.almeida.henrique.gym_tracking.services.CustomerService
import io.swagger.annotations.*
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
    @ApiOperation(value = "Return just one customer")
    @ApiResponses(
        ApiResponse(
            code = 200,
            message = "Found one customer",
            response = CustomerDTO::class,
            responseContainer = "Map"
        )
    )
    fun findById(
        @ApiParam(value = "Id of customer account", example = "4e4eeb3948198f4fdf3bfbb46a67aaa077e5f82a")
        @PathVariable id: String
    ): ResponseEntity<CustomerDTO> = ResponseEntity.ok().body(CustomerDTO(service.findById(id)))

    @GetMapping("$RESOURCE/email", produces = [APPLICATION_JSON])
    @ApiOperation(value = "Returns the customer with this email")
    @ApiResponse(code = 200, message = "Ok", response = CustomerDTO::class, responseContainer = "Map")
    fun findByEmail(
        @RequestParam(required = true)
        @ApiParam(value = "Email of customer", example = "example@email.com", required = true)
        email: String
    ) = ResponseEntity.ok().body(CustomerDTO(service.findByEmail(email)))

    @GetMapping(RESOURCE, produces = [APPLICATION_JSON])
    @ApiOperation(value = "Returns a list of customers")
    @ApiResponses(
        ApiResponse(code = 200, message = "Found customers", response = CustomerDTO::class, responseContainer = "List")
    )
    fun findByFirstNameOrFullName(
        @RequestParam(required = false)
        @ApiParam(value = "First name of customer", example = "eder", required = false)
        firstname: String?,
        @RequestParam(required = false)
        @ApiParam(value = "Last name of customer", example = "jofre", required = false)
        lastname: String?,
    ): ResponseEntity<List<CustomerDTO>> {
        return if ((firstname.isNullOrEmpty() || firstname.isBlank()) && (lastname.isNullOrEmpty() || lastname.isBlank()))
            ResponseEntity.ok().body(this.listCustomerToListDto(service.findAll()))
        else if ((lastname.isNullOrEmpty() || lastname.isBlank()))
            ResponseEntity.ok().body(this.listCustomerToListDto(service.findByFirstNameRegex(firstname)))
        else
            ResponseEntity.ok().body(this.listCustomerToListDto(service.findByFullNameRegex(firstname, lastname)))
    }

    @PostMapping(RESOURCE, produces = [APPLICATION_JSON], consumes = [APPLICATION_JSON])
    @ApiOperation(value = "Register a new customer")
    @ApiResponses(
        ApiResponse(code = 201, message = "Customer created", response = CustomerDTO::class, responseContainer = "Map")
    )
    fun insertCustomer(@RequestBody customerDTO: CustomerDTO): ResponseEntity<CustomerDTO> {
        var customer = service.fromDTO(customerDTO)
        customer.id = ObjectId.get().toString()
        customer = service.insert(customer)
        val uri: URI = ServletUriComponentsBuilder
            .fromCurrentRequest().path("{/id}").buildAndExpand(customer.id).toUri()
        return ResponseEntity.created(uri).body(customerDTO)
    }

    @DeleteMapping("$RESOURCE/{id}", produces = [APPLICATION_JSON])
    @ApiOperation(value = "Delete a customer through their id")
    @ApiResponse(code = 200, message = "Customer successfully deleted", response = Unit::class)
    fun deleteCustomer(
        @ApiParam(
            value = "Id of customer account",
            example = "4e4eeb3948198f4fdf3bfbb46a67aaa077e5f82a"
        )
        @PathVariable id: String
    ): ResponseEntity<Map<String, Any>> {
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

    @PutMapping("$RESOURCE/{id}", consumes = [APPLICATION_JSON], produces = [APPLICATION_JSON])
    @ApiOperation(value = "Updates a customer through their id")
    @ApiResponse(
        code = 200, message = "Ok", response = Unit::class
    )
    fun updateCustomer(
        @RequestBody customerDTO: CustomerDTO,
        @ApiParam(value = "Id of customer account", example = "4e4eeb3948198f4fdf3bfbb46a67aaa077e5f82a")
        @PathVariable id: String
    ): ResponseEntity<Map<String, Any>> {
        val customer = service.fromDTO(customerDTO)
        customer.id = id
        service.update(customer)

        return ResponseEntity.ok().body(
            mapOf(
                "id" to id,
                "message" to "Customer successfully updated",
                "status" to 200
            )
        )
    }

    private fun listCustomerToListDto(listCustomer: List<Customer>): List<CustomerDTO> {
        return listCustomer.stream().map { CustomerDTO(it) }.toList()
    }
}
