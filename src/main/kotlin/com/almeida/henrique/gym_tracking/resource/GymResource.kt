package com.almeida.henrique.gym_tracking.resource

import com.almeida.henrique.gym_tracking.dto.GymDTO
import com.almeida.henrique.gym_tracking.services.GymService
import io.swagger.annotations.Api
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI
import java.util.stream.Collectors
import kotlin.streams.toList

@RestController
@RequestMapping("/api/v1")
@Api(value = "Gym", description = "Gym related operations")
class GymResource {

    @Autowired
    private lateinit var service: GymService

    @GetMapping("/gym")
    fun findAll(): ResponseEntity<List<GymDTO>> {
        return ResponseEntity.ok()
            .body(service.findAll().stream().map { GymDTO(it) }.toList())
    }

    @GetMapping("/gym/{id}")
    fun finbById(@PathVariable id: String): ResponseEntity<GymDTO> {
        return ResponseEntity.ok().body(GymDTO(service.findById(id)))
    }

    @PostMapping("/gym")
    fun insert(@RequestBody gymDTO: GymDTO): ResponseEntity<GymDTO> {
        var gym = service.fromDTO(gymDTO)
        gym.id = ObjectId.get().toString()
        gym = service.insert(gym)
        val uri: URI = ServletUriComponentsBuilder
            .fromCurrentRequest().path("{/id}").buildAndExpand(gym.id).toUri()
        return ResponseEntity.created(uri).body(gymDTO)
    }

    @DeleteMapping("/gym/{id}")
    fun delete(@PathVariable id: String): ResponseEntity<String> {
        service.delete(id)
        return ResponseEntity.ok().body("Gym successfully deleted: id $id")
    }

    @PutMapping("/gym/{id}")
    fun update(@RequestBody gymDTO: GymDTO, @PathVariable id: String): ResponseEntity<GymDTO> {
        val gym = service.fromDTO(gymDTO)
        gym.id = id
        service.update(gym)
        return ResponseEntity.ok().body(gymDTO)
    }
}
