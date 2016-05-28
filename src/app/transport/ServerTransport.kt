package app.transport

import app.controller.Controller
import app.model.Lobby
import java.net.Socket

/**
 * Created by sech0614 on 25.05.2016.
 */
class ServerTransport(socket: Socket, lobby: Lobby) : Transport(socket) {
    var controller: Controller? = null
    var lobby: Lobby
    init {
        this.lobby = lobby
    }
    fun listen() {
        controller = Controller(this, lobby)
        startEventLoop();
    }

    override fun handle(inputData: String) {
        controller?.handleMessage(inputData)
    }
}




