/*
package data.network.internetService

import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

//const val ENDPOINT = "https://api2.kiparo.com/"
//const val JSON_URI = "static/it_news.json"
//const val XML_URI = "static/it_news.xml"

class ApplyData() {
    var strFromKiparo: String = ""
    lateinit var th: Thread

    fun getStrFromKiparoUseOkHttp(str: String) {
        th = Thread {
            val okHttp = OkHttpClient()
            val request: Request = Request.Builder()
                .url(ENDPOINT + str)
                .build()
            okHttp.newCall(request).execute().use { response ->
                println("Used Okhttp")
                strFromKiparo = response.body?.string() ?: "Error"
            }
        }
        th.start()
    }

    fun getStrFromKiparoUseUrlConnection(str: String) {
        th = Thread {
            val httpURLConnection = URL(ENDPOINT + str).openConnection() as HttpURLConnection
            httpURLConnection.apply {
                connectTimeout = 10000 //10 секунд таймаут
                requestMethod = "GET"
                doInput = true
            }
            if (httpURLConnection.responseCode != HttpURLConnection.HTTP_OK)
                strFromKiparo = "error"
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
            strFromKiparo = text
        }
        th.start()
    }
}*/
