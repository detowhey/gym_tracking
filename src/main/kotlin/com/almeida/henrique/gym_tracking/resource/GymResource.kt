package com.almeida.henrique.gym_tracking.resource

import com.almeida.henrique.gym_tracking.entities.Gym
import com.almeida.henrique.gym_tracking.services.GymService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/gym"])
class GymResource {

    @Autowired
    private lateinit var service: GymService

    @GetMapping
    fun findAll(): ResponseEntity<List<Gym>> = ResponseEntity.ok().body(service.findAll())

    @GetMapping(value = ["/{id}"])
    fun finbById(@PathVariable id: String): ResponseEntity<Gym> = ResponseEntity.ok().body(service.findById(id))
}