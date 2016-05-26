package app.model

import app.transport.ServerTransport
import app.transport.Transport

/**
 * Created by sech0614 on 25.05.2016.
 */
class Subscriber(name: String, transport: ServerTransport) {
    val name: String
    private var transport: Transport
    init {
        this.transport = transport
        this.name = name
    }
    fun send(obj: String) {
        transport.sendEvent(obj)
    }
}