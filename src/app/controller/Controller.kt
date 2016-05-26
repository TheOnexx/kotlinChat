package app.controller

import app.transport.ServerTransport

/**
 * Created by sech0614 on 25.05.2016.
 */
class Controller(serverTransport: ServerTransport) {
    private val serverTransport: ServerTransport
    init {
        this.serverTransport = serverTransport
    }
    fun handleMessage(message: String) {

    }
}