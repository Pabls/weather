package com.ar4i.weather.data.mappers

import com.ar4i.weather.R
import com.ar4i.weather.data.repositories.resources.IResourcesRepository
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object ErrorHandler : IErrorHandler {

    private val NETWORK_EXCEPTIONS = listOf(
        UnknownHostException::class.java,
        SocketTimeoutException::class.java,
        ConnectException::class.java
    )

    private lateinit var resourceRepository: IResourcesRepository

    fun setResourceRepository(resourceRepository: IResourcesRepository) {
        this.resourceRepository = resourceRepository
    }


    override fun getErrorMessage(t: Throwable): String {
        return if (NETWORK_EXCEPTIONS.contains(t::class.java)) {
            resourceRepository.getStringById(R.string.error_check_network_connection)
        } else {
            resourceRepository.getStringById(R.string.error_try_later)
        }
    }
}