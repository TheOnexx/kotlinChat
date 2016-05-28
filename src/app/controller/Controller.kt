package app.controller

import app.model.ServerJsonParser
import app.transport.ServerTransport

/**
 * Created by sech0614 on 25.05.2016.
 */
class Controller(serverTransport: ServerTransport) {
    private val serverTransport: ServerTransport
    private var jsonParser: ServerJsonParser
    init {
        this.serverTransport = serverTransport
        jsonParser = ServerJsonParser.instance

    }
    fun handleMessage(message: String) {
        jsonParser.process(message)
        println(jsonParser.jsonType)
    }
}