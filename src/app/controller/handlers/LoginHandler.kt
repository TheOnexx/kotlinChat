package app.controller.handlers

import app.controller.Controller
import app.controller.RequestHandler
import app.model.Subscriber

/**
 * Created by TheOne on 27.05.16.
 */
class LoginHandler: RequestHandler {
    override fun handleEvent(controller: Controller, message: String) {
        var jsonParser = controller.jsonParser
        var login = jsonParser.getLogin()
        var subscriber = Subscriber(login?: "User${controller.lobby.userCount + 1}", controller.serverTransport)
        if(!controller.lobby.addUser(subscriber)) {
            subscriber.send(jsonParser.createError("User with name: ${subscriber.name} is already exist"))
        } else {
            subscriber.send(jsonParser.createCustomMessage("login", subscriber.name))
        }
        controller.lobby.sendPublicMessage(subscriber, jsonParser.createCustomMessage("message",
                "${subscriber.name} has joined the chat"))
    }
}

