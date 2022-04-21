package com.almeida.henrique.gym_tracking.exception

class DataBaseException(message: String) : RuntimeException(message) {
    companion object {
        private const val serialVersionUID: Long = 1L
    }
}
