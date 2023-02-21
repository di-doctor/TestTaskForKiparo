package data.repositorys

import data.network.JSON_URI
import data.network.RemoteDataInterface
import domain.models.ModelData
import domain.repositorys.RepoJsonOkHttpInterface

class RepoJsonOkHttpImp(val remoteD: RemoteDataInterface): RepoJsonOkHttpInterface {

    override fun getData(): ModelData {
        remoteD.getData(JSON_URI)
        remoteD.th.join()
        return ModelData(remoteD.dataFromKiparo.data)
    }
}