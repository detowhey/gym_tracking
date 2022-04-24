package com.almeida.henrique.gym_tracking.resource

import com.almeida.henrique.gym_tracking.domain.Gym
import com.almeida.henrique.gym_tracking.dto.GymDTO
import com.almeida.henrique.gym_tracking.services.GymService
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
@Api(value = "Gym", tags = ["Gym"])
class GymResource {

    companion object {
        private const val RESOURCE = "/gym"
        private const val APPLICATION_JSON = " application/json"
    }

    @Autowired
    private lateinit var service: GymService

    @GetMapping(RESOURCE, produces = [APPLICATION_JSON])
    @ApiOperation(value = "Returns a list of gyms")
    @ApiResponses(
        ApiResponse(code = 200, message = "Found gyms", response = GymDTO::class, responseContainer = "List")
    )
    fun findGyms(
        @RequestParam(required = false)
        @ApiParam(value = "Name's gym", example = "Temple Gym", required = false)
        name: String?,
        @RequestParam(required = false)
        @ApiParam(value = "Gym opening hours", example = "08:00-23:00", required = false)
        gymOpeningTime: String?,
    ): ResponseEntity<List<GymDTO>> {
        return if ((name.isNullOrEmpty() || name.isBlank()) && (gymOpeningTime.isNullOrEmpty() || gymOpeningTime.isBlank()))
            ResponseEntity.ok().body(this.listGymToListDto(service.findAll()))
        else if ((gymOpeningTime.isNullOrEmpty() || gymOpeningTime.isBlank()))
            ResponseEntity.ok().body(this.listGymToListDto(service.findByName(name)))
        else if (name.isNullOrEmpty() || name.isBlank())
            ResponseEntity.ok(this.listGymToListDto(service.findByOpeningHours(gymOpeningTime)))
        else
            ResponseEntity.ok().body(this.listGymToListDto(service.findByNameAndOpeningHours(name, gymOpeningTime)))
    }

    @GetMapping("$RESOURCE/payment", produces = [APPLICATION_JSON])
    @ApiOperation(value = "Returns a list of gym with respective monthly fees")
    @ApiResponse(code = 200, message = "Ok", response = GymDTO::class, responseContainer = "List")
    fun findByPrice(
        @RequestParam(required = true)
        @ApiParam(value = "Initial price", example = "50.00", required = true)
        initPrice: Double,
        @RequestParam(required = true)
        @ApiParam(value = "Final price", example = "150.00", required = true)
        finalPrice: Double
    ): ResponseEntity<List<GymDTO>> {
        return ResponseEntity.ok(this.listGymToListDto(service.findByPriceBetween(initPrice, finalPrice)))
    }

    @GetMapping("$RESOURCE/{id}", produces = [APPLICATION_JSON])
    @ApiOperation(value = "Return just one gym")
    @ApiResponses(
        ApiResponse(
            code = 200,
            message = "Found one gym",
            response = GymDTO::class,
            responseContainer = "Map"
        )
    )
    fun finbById(
        @ApiParam(value = "Id of gym account", example = "4e4eeb3948198f4fdf3bfbb46a67aaa077e5f82a")
        @PathVariable id: String
    ): ResponseEntity<GymDTO> = ResponseEntity.ok().body(GymDTO(service.findById(id)))


    @PostMapping(RESOURCE, produces = [APPLICATION_JSON], consumes = [APPLICATION_JSON])
    @ApiOperation(value = "Register a new gym")
    @ApiResponses(
        ApiResponse(code = 201, message = "Gym created", response = GymDTO::class, responseContainer = "Map")
    )
    fun insertGym(@RequestBody gymDTO: GymDTO): ResponseEntity<GymDTO> {
        var gym = service.fromDTO(gymDTO)
        gym.id = ObjectId.get().toString()
        gym = service.insert(gym)
        val uri: URI = ServletUriComponentsBuilder
            .fromCurrentRequest().path("{/id}").buildAndExpand(gym.id).toUri()
        return ResponseEntity.created(uri).body(gymDTO)
    }

    @DeleteMapping("$RESOURCE/{id}", produces = [APPLICATION_JSON])
    @ApiOperation(value = "Delete a gym through their id")
    @ApiResponse(code = 200, message = "Gym successfully deleted", response = Unit::class)
    fun deleteGym(
        @ApiParam(
            value = "Id of gym account",
            example = "4e4eeb3948198f4fdf3bfbb46a67aaa077e5f82a"
        )
        @PathVariable id: String
    ): ResponseEntity<Map<String, Any>> {
        service.delete(id)
        return ResponseEntity.ok()
            .body(
                mapOf(
                    "id" to id,
                    "message" to "Gym successfully deleted",
                    "status" to 200
                )
            )
    }

    @PutMapping("$RESOURCE/{id}", consumes = [APPLICATION_JSON], produces = [APPLICATION_JSON])
    @ApiOperation(value = "Updates a gym data through their id")
    @ApiResponse(
        code = 200, message = "Ok", response = Unit::class
    )
    fun updateGym(@RequestBody gymDTO: GymDTO, @PathVariable id: String): ResponseEntity<Map<String, Any>> {
        val gym = service.fromDTO(gymDTO)
        gym.id = id
        service.update(gym)
        return ResponseEntity.ok().body(
            mapOf(
                "id" to id,
                "message" to "Gym data successfully updated",
                "status" to 200
            )
        )
    }

    private fun listGymToListDto(listGym: List<Gym>): List<GymDTO> {
        return listGym.stream().map { GymDTO(it) }.toList()
    }
}
