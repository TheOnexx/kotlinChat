package app.controller

import app.model.Lobby
import app.model.ServerJsonParser
import app.transport.ServerTransport

/**
 * Created by sech0614 on 25.05.2016.
 */
class Controller(serverTransport: ServerTransport, lobby: Lobby) {
    val serverTransport: ServerTransport
    val jsonParser: ServerJsonParser
    private var handlerFactory: HandlerFactory
    var lobby: Lobby
    init {
        this.serverTransport = serverTransport
        jsonParser = ServerJsonParser.instance
        handlerFactory = HandlerFactory.instance
        this.lobby = lobby
    }
    fun handleMessage(message: String) {
        jsonParser.process(message)
        try {
            var requestHandler = getRequestHandler(jsonParser.jsonType)
            requestHandler.handleEvent(this, message)
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }

    private fun getRequestHandler(jsonType: String?): RequestHandler {
        return handlerFactory.getHandler(jsonType);
    }
}