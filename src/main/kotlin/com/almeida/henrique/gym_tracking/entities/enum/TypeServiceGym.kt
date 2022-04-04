package com.almeida.henrique.gym_tracking.entities.enum

enum class TypeServiceGym(val code: Int, name: String) {

    BODYBUIILDING(1, "Bobybuilding"),
    COMBAT_SPORTS(2, "Combat sports"),
    FITNESS(3, "Fitness"),
    POWERLIFTTING(4, "Powerlifting");


    companion object {
        fun valueOf(code: Int): TypeServiceGym {
            return values().find { it.code == code } ?: throw IllegalArgumentException("Invalide Type Service Gym Code")
        }

        fun getName(code: Int): String = valueOf(code).name
    }
}
