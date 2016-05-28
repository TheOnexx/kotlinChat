package app.model


import java.util.*

/**
 * Created by sech0614 on 25.05.2016.
 */
class Lobby(val dataFile: String) {
    private var ioModule: ChatIO
    private var userList: MutableList<Subscriber> = ArrayList();
    val userCount = userList.size
    init {
        ioModule = ChatIO(dataFile)
    }
    fun addUser(subscriber: Subscriber): Boolean{
        if(userList.contains(subscriber)) {
            return false
        }
        return userList.add(subscriber)
    }
    fun removeUser(subscriber: Subscriber): Boolean {
        return userList.remove(subscriber)
    }

    fun sendPublicMessage(subscriber: Subscriber, message: String) {
        userList.forEach {
            if(it.name != subscriber.name)
                it.send(message)
        }
    }
    fun findSubscriberByName(name: String?): Subscriber {
        return userList.find { it.name == name } ?: throw IllegalStateException("There is no such user in the list")
    }


}

