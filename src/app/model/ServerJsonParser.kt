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

    val jsonType: String
        get() = getCustomEntity("type")

    fun getCustomEntity(property: String): String {
        return request?.get(property)?.asString ?: throw IllegalArgumentException("Invalid request")
    }

    fun createError(errorMsg: String): String {
        return createCustomMessage("error", errorMsg)
    }

    fun createCustomMessage(property: String, value: String): String {
        var json = JsonObject()
        json = appendCustomProperty(json, property, value)
        return json.toString()
    }

    private fun appendCustomProperty(json: JsonObject, property: String, value: String): JsonObject {
        json.addProperty(property, value)
        return json
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
