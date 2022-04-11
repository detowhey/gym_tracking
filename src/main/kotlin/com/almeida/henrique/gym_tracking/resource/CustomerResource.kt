package com.almeida.henrique.gym_tracking.resource

import com.almeida.henrique.gym_tracking.dto.CustomerDTO
import com.almeida.henrique.gym_tracking.services.CustomerService
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI
import java.util.stream.Collectors

@RestController
@RequestMapping("/api/v1")
class CustomerResource {

    @Autowired
    private lateinit var service: CustomerService

    @GetMapping("/customer")
    fun findAll(): ResponseEntity<List<CustomerDTO>> {
        return ResponseEntity.ok()
            .body(service.findAll().stream().map { CustomerDTO(it) }.collect(Collectors.toList()))
    }

    @GetMapping("/customer/{id}")
    fun findById(@PathVariable id: String): ResponseEntity<CustomerDTO> {
        return ResponseEntity.ok().body(CustomerDTO(service.findById(id)))
    }

    @PostMapping("/customer")
    fun insert(@RequestBody customerDTO: CustomerDTO): ResponseEntity<CustomerDTO> {
        var customer = service.fromDTO(customerDTO)
        customer.id = ObjectId.get().toString()
        customer = service.insert(customer)
        val uri: URI = ServletUriComponentsBuilder
            .fromCurrentRequest().path("{/id}").buildAndExpand(customer.id).toUri()
        return ResponseEntity.created(uri).body(customerDTO)
    }

    @DeleteMapping("/customer/{id}")
    fun delete(@PathVariable id: String): ResponseEntity<String> {
        service.delete(id)
        return ResponseEntity.ok().body("Customer successfully deleted: id $id")
    }

    @PutMapping(value = ["/customer/{id}"])
    fun update(@RequestBody customerDTO: CustomerDTO, @PathVariable id: String): ResponseEntity<CustomerDTO> {
        val customer = service.fromDTO(customerDTO)
        customer.id = id
        service.update(customer)
        return ResponseEntity.ok().body(customerDTO)
    }
}