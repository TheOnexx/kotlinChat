package app.model

import com.sun.org.apache.xerces.internal.impl.xs.SchemaNamespaceSupport
import java.io.*
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Created by sech0614 on 26.05.2016.
 */
class ChatIO(dataFile: String) {
    val pattern: Pattern = Pattern.compile("\\[(.+)\\]\\[(.+)\\]")
    val dataFile: String;
    init {
        this.dataFile = dataFile;
    }
    fun writeSubscriber(subscriber: Subscriber) {
        writeData(subscriber)
    }

    private fun writeData(subscriber: Subscriber) {
        PrintWriter(FileWriter(File(dataFile), true)).use {
            it.append("[${subscriber.name}]").append("[${subscriber.password}]")
            it.append("\n")
            it.flush()
        }
    }

    fun check(index: Int, value: String):Boolean {
        Scanner(File(dataFile)).use {
            var res = getString(it, value, index)
            println("inputValue: $value, Result: $res")
            if(res != null) {
                return value == res
            }
        }
        return false
    }

    private fun getString(scanner: Scanner, name: String, index: Int): String? {
        do {
            if(!scanner.hasNext()) return null
            var line = scanner.nextLine()
            println("FileLine: $line")
            var matcher = pattern.matcher(line)
            if(!matcher.matches()) return null
            if(name == matcher.group(index)) return name
            else continue
        } while(line != null)
        return null
    }
}