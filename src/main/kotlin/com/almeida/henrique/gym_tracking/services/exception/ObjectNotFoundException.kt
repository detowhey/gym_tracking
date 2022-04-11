package com.almeida.henrique.gym_tracking.services.exception

class ObjectNotFoundException(message: String = "Object not found") : RuntimeException(message) {
    companion object {
        private const val serialVersionUID = 1L
    }
}
