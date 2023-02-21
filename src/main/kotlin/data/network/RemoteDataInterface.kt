package data.network

import data.network.models.ModelRemote

const val ENDPOINT = "https://api2.kiparo.com/"
const val JSON_URI = "static/it_news.json"
const val XML_URI = "static/it_news.xml"

interface RemoteDataInterface {
    var dataFromKiparo: ModelRemote
    var th: Thread
    fun getData(postFix: String): Unit
}