package app.transport

import app.controller.Controller
import app.model.Lobby
import java.net.Socket

/**
 * Created by sech0614 on 25.05.2016.
 */
class ServerTransport(socket: Socket, lobby: Lobby) : Transport(socket) {
    var controller: Controller? = null
    fun listen() {
        controller = Controller(this)
        startEventLoop();
    }

    override fun handle(inputData: String) {
        controller?.handleMessage(inputData)
    }
}




