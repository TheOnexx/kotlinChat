package app.controller

/**
 * Created by TheOne on 27.05.16.
 */
interface RequestHandler {
    fun handleEvent(controller: Controller, message: String);
}