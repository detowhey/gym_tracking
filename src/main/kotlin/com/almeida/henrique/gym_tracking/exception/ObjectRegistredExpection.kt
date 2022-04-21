package com.almeida.henrique.gym_tracking.exception

class ObjectRegistredExpection(message: String = "Object already registered with this email") :
    RuntimeException(message)
