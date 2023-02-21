package data.network

import data.network.models.ModelRemote
import okhttp3.OkHttpClient
import okhttp3.Request

class UsesOkhttpImp() : RemoteDataInterface {
    override lateinit var dataFromKiparo: ModelRemote
    override lateinit var th: Thread

    override fun getData(postFix:String) {

        th = Thread {
            val okHttp = OkHttpClient()
            val request: Request = Request.Builder()
                .url(ENDPOINT + postFix)
                .build()
            okHttp.newCall(request).execute().use { response ->
                println("Used Okhttp")
                dataFromKiparo = ModelRemote(response.body?.string() ?: "Error")
            }
        }
        th.start()
    }
}