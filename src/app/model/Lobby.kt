package app.model


import java.util.*

/**
 * Created by sech0614 on 25.05.2016.
 */
class Lobby(val dataFile: String) {
    private var ioModule: ChatIO
    private var userList: MutableMap<String, Subscriber> = HashMap();
    val userCount = userList.size
    init {
        ioModule = ChatIO(dataFile)
    }
    fun addUser(subscriber: Subscriber): Boolean {
        synchronized(userList) {
            if (userList.containsKey(subscriber.name)) {
                println("Contains")
                return false
            }
            println("Not Contains")
            userList.put(subscriber.name, subscriber)
            return true
        }
    }
    fun removeUser(subscriber: Subscriber) {
        userList.remove(subscriber.name)
    }

    fun checkUserExists(subscriber: Subscriber): Boolean {
        return ioModule.check(1, subscriber.name)
    }
    fun checkPassword(subscriber: Subscriber): Boolean {
        return ioModule.check(2, subscriber.password)
    }

    fun sendPublicMessage(subscriber: Subscriber, message: String) {
        userList.forEach {
            if(it.value.name != subscriber.name)
                it.value.send(message)
        }
    }
    fun findSubscriberByName(name: String?): Subscriber {
        return userList[name] ?: throw IllegalStateException("There is no such user in the list")
    }

    fun writeUser(subscriber: Subscriber) {
        ioModule.writeSubscriber(subscriber)
    }


}

