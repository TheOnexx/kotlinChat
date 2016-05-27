package app.model


import java.util.*

/**
 * Created by sech0614 on 25.05.2016.
 */
class Lobby(val dataFile: String) {
    private var ioModule: ChatIO
    private var userList: MutableList<Subscriber> = ArrayList();
    private var userCount: Int = 0;
    init {
        ioModule = ChatIO(dataFile)
    }
    fun addUser(subscriber: Subscriber): Boolean{
        if(userList.contains(subscriber)) {
            throw UserExistException("Such User is already exists")
        }
        return userList.add(subscriber)
    }
    fun removeUser(subscriber: Subscriber): Boolean {
        return userList.remove(subscriber)
    }

    fun sendPublicMessage(subscriber: Subscriber, message: String) {
        userList.forEach {
            if(it.name == subscriber.name)
                it.send(message)
        }
    }


}

class UserExistException(s: String) : Throwable() {

}
