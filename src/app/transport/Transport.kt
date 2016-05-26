package app.transport

import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.Socket
import java.net.SocketTimeoutException

/**
 * Created by sech0614 on 25.05.2016.
 */
open class Transport {
    private var input: ObjectInputStream;
    private var output: ObjectOutputStream
    constructor(socket: Socket) {
        socket.soTimeout = 500
        input = ObjectInputStream(socket.inputStream)
        output = ObjectOutputStream(socket.outputStream)

    }

    fun startEventLoop() {
        Thread(Runnable { run {
            while(!Thread.interrupted()) {
                try {
                    handle(input.readObject().toString())
                } catch (e: SocketTimeoutException) {
                    Thread.`yield`();
                }
            }
        } }).start();
    }
    open fun handle(inputData: String) {}

    fun sendEvent(obj: String) {
        output.writeObject(obj)
    }
}