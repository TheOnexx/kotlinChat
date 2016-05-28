package app.controller.handlers

import app.controller.Controller
import app.controller.RequestHandler

/**
 * Created by TheOne on 27.05.16.
 */
class MessageHandler : RequestHandler {
    override fun handleEvent(controller: Controller, message: String) {
        val json = controller.jsonParser
        val subscriber = controller.lobby.findSubscriberByName(json.getCustomEntity("login"))
        val msg = controller.jsonParser.getCustomEntity("data")
        controller.lobby.sendPublicMessage(subscriber, json.createUserMessage(subscriber.name, msg))
    }
}