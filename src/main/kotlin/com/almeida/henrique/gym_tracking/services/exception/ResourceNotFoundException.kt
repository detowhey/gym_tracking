package com.almeida.henrique.gym_tracking.services.exception

class ResourceNotFoundException(id: Any) : RuntimeException("Resource not found. Id $id") {
    companion object {
        private const val serialVersionUID = 1L
    }
}
