package com.bergamin.contactlist.util

import java.io.IOException
import java.io.PrintStream
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.*

/**
 * Created by Guilherme Taffarel Bergamin on 29/11/2017.
 */
class WebClient {
    fun post(json: String): String? {
        try {
            val serverPath = "Fill in this String with your own server path"
            val connection = URL(serverPath).openConnection() as HttpURLConnection

            connection.setRequestProperty("Content-type", "application/json")
            connection.setRequestProperty("Accept", "application/json")
            connection.doOutput = true

            PrintStream(connection.outputStream).println(json)
            connection.connect()

            return Scanner(connection.inputStream).next()
        }catch(e: MalformedURLException){
            e.printStackTrace()
        }catch(e: IOException){
            e.printStackTrace()
        }
        return null
    }
}
