package data.network

import data.network.models.ModelRemote
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class UsesUrlConnectionImp() : RemoteDataInterface {
    override lateinit var dataFromKiparo: ModelRemote
    override lateinit var th: Thread

    override fun getData(postFix:String) {
        th = Thread {
            val httpURLConnection = URL(ENDPOINT + postFix).openConnection() as HttpURLConnection
            httpURLConnection.apply {
                connectTimeout = 10000 //10 секунд таймаут
                requestMethod = "GET"
                doInput = true
            }
            if (httpURLConnection.responseCode != HttpURLConnection.HTTP_OK)
                dataFromKiparo = ModelRemote("error")
            val streamReader = InputStreamReader(httpURLConnection.inputStream)
            var text = ""
            try {
                streamReader.readText().also { text = it }
            } catch (e: IOException) {
                println("Read Error")
            } finally {
                streamReader.close()
            }
            println("Used UrlConnection")
            dataFromKiparo = ModelRemote(text)
        }
        th.start()
    }
}