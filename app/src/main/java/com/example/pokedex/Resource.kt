package com.example.pokedex

data class Resource<out T>(val status: ResourceStatus, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): Resource<T> = Resource(status = ResourceStatus.SUCCESS, data = data, message = null)

        fun error(message: String?) = Resource(status = ResourceStatus.ERROR, data = null, message = message)

        fun <T> loading(data: T? = null) = Resource(status = ResourceStatus.LOADING, data = data, message = null)
    }
}