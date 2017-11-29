package com.bergamin.contactlist.util

import java.io.PrintStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

/**
 * Created by gbergamin on 29/11/2017.
 */
class WebClient {
    fun post(json: String): String? {
        var connection = URL("serverPath").openConnection() as HttpURLConnection

        connection.setRequestProperty("Content-type","application/json")
        connection.setRequestProperty("Accept","application/json")
        connection.doOutput = true

        PrintStream(connection.outputStream).println(json)
        connection.connect()

        return Scanner(connection.inputStream).next()
    }
}