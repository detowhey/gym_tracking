package com.almeida.henrique.gym_tracking

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@SpringBootApplication
@EnableMongoRepositories
class GymTrackingApplication

fun main(args: Array<String>) {
	runApplication<GymTrackingApplication>(*args)
}
