package app.controller.handlers

import app.controller.Controller
import app.controller.RequestHandler
import app.controller.exception.IncorrectCredentialsException
import app.model.Lobby
import app.model.ServerJsonParser
import app.model.Subscriber

/**
 * Created by TheOne on 27.05.16.
 */
class LoginHandler: RequestHandler {
    override fun handleEvent(controller: Controller, message: String) {
        var lobby = controller.lobby
        var jsonParser = controller.jsonParser
        var login = jsonParser.getLogin()
        var subscriber = Subscriber(login?: "User${lobby.userCount + 1}", controller.serverTransport)
        addOrCheckUser(lobby, subscriber)
        addIntoLobby(jsonParser, lobby, subscriber)
        lobby.sendPublicMessage(subscriber, jsonParser.createCustomMessage("message",
                "${subscriber.name} has joined the chat"))
    }

    private fun addIntoLobby(jsonParser: ServerJsonParser, lobby: Lobby, subscriber: Subscriber) {
        if (!lobby.addUser(subscriber)) {
            subscriber.send(jsonParser.createError("User with name: ${subscriber.name} is already exist"))
        } else {
            subscriber.send(jsonParser.createCustomMessage("login", subscriber.name))
        }
    }

    private fun addOrCheckUser(lobby: Lobby, subscriber: Subscriber) {
        if(lobby.checkUserExists(subscriber))  {
            if(!lobby.checkPassword(subscriber))
                throw IncorrectCredentialsException("Incorrect Password")
        } else {
            lobby.writeUser(subscriber)
        }
    }
}

