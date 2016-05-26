package app

import app.model.Lobby
import app.transport.ServerTransport
import app.transport.Transport
import java.net.ServerSocket
import java.net.Socket

/**
 * Created by sech0614 on 25.05.2016.
 */
class Server {
    fun init(socket: ServerSocket) {
        var lobby = Lobby("data.txt");
        while(!Thread.interrupted())
            ServerTransport(socket.accept(), lobby).listen()
    }
}

fun main(args: Array<String>) {
    Server().init(ServerSocket(1024))
}