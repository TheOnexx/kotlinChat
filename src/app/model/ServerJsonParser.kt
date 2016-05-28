package app.model

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser

/**
 * Created by TheOne on 26.03.16.
 */
class ServerJsonParser private constructor() {
    private val json: JsonParser
    private var request: JsonObject? = null

    init {
        json = JsonParser()
    }

    fun process(receive: String) {
        println(receive.toString())
        request = json.parse(receive.toString()).asJsonObject
    }

    val jsonType: String?
        get() = getCustomEntity("type")

    fun getCustomEntity(property: String): String {
        return request?.get(property)?.asString ?: throw IllegalArgumentException("Invalid request")
    }

    fun createError(errorMsg: String): String {
        return createHeaderType("error").appendJSONCustomProperty("message", errorMsg).toString()
    }

    fun createCustomMessage(property: String, value: String): String {
        var json = JsonObject()
        return json.appendJSONCustomProperty(property, value).toString()
    }

    fun createUserMessage(login: String, message: String): String {
        return createHeaderType("message").
                appendJSONCustomProperty("login", login).
                appendJSONCustomProperty("message", message).toString();
    }

    fun JsonObject.appendJSONCustomProperty(property: String, value: String): JsonObject {
        this.addProperty(property, value)
        return this;
    }

    private fun createHeaderType(type: String): JsonObject {
        var json = JsonObject()
        return json.appendJSONCustomProperty("type", type)
    }

    fun createListMessage(allClients: List<Subscriber>): String {
        val json = JsonObject()
        val array = JsonArray()
        json.addProperty("type", "users")
        for (c in allClients) {
            val curObj = JsonObject()
            json.addProperty("nick", c.name)
            array.add(curObj)
        }
        json.add("data", array)
        return json.toString()
    }
    fun getLogin() : String? {
        try {
            return getCustomEntity("login")
        } catch (e: IllegalArgumentException) {
            return null
        }
    }

    companion object {
        private val parser: ServerJsonParser? = null
        val instance: ServerJsonParser
            get() {
                if (parser == null) {
                    return ServerJsonParser()
                }
                return parser
            }
    }
}

