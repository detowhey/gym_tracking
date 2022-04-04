package com.almeida.henrique.gym_tracking.resource.excepetions

import com.fasterxml.jackson.annotation.JsonFormat
import java.io.Serializable
import java.time.Instant

data class StandardError(
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-/MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    val timestamp: Instant,
    val status: Int,
    val error: String,
    val message: String?,
    val path: String
) : Serializable {
    companion object {
        private const val serialVersionUID: Long = 1L
    }
}
