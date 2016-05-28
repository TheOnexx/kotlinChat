package app.controller

import app.controller.handlers.LoginHandler
import app.controller.handlers.MessageHandler
import java.util.*

/**
 * Created by TheOne on 27.05.16.
 */
class HandlerFactory {
    private constructor() {}
    val handlers: MutableMap<String, RequestHandler>;
    init {
        handlers = HashMap();
        handlers.put("register", LoginHandler())
        handlers.put("message", MessageHandler())
    }
    fun getHandler(type: String?) = handlers[type]
            ?: throw IllegalArgumentException("There is no handler that match request type")
    companion object {
        private val factory: HandlerFactory? = null;
        val instance: HandlerFactory
        get() {
            if(factory == null) {
                return HandlerFactory()
            }
            return factory
        }
    }
}