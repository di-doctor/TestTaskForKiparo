package data.repositorys

import data.network.JSON_URI
import data.network.RemoteDataInterface
import domain.models.ModelData
import domain.repositories.RepoJsonUrlConnectionInterface

class RepoJsonUrlConnectionImp(private val remoteD: RemoteDataInterface): RepoJsonUrlConnectionInterface {
    override fun getData(): ModelData {
        remoteD.getData(JSON_URI)
        //В основном потоке можно делать  полезную работу.
        remoteD.th.join() //Ждем пока придут данные
        return ModelData(remoteD.dataFromKiparo.data)   // Маппинг из модельки представленной в Data слое  в модельку представленная в слое domain
    }
}