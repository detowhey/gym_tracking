package com.almeida.henrique.gym_tracking.entities.enum

enum class TypeServiceGym(val code: Int) {

    BODYBUIILDING(1), COMBAT_SPORTS(2), FITNESS(3), POWERLIFT(4);


    companion object {
        fun valueOf(code: Int): TypeServiceGym {
            return values().find { it.code == code } ?: throw IllegalArgumentException("Invalide Type Service Gym Code")
        }
    }
}
