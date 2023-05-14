package com.devst.a5reciver

import java.net.HttpURLConnection
import java.net.URL

//cride code->unsecure and unoptimised


fun getDtaFromNetwork(url:String):String{
    val urlConnection=URL(url).openConnection() as HttpURLConnection
    try {
        return urlConnection.inputStream.bufferedReader().readText()

    }finally {
        urlConnection.disconnect()
    }
}