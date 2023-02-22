package data.repositorys

import data.network.RemoteDataInterface
import data.network.XML_URI
import domain.models.ModelData
import domain.repositories.RepoXmlUrlConnectionInterface

class RepoXmlUrlConnectionImp(private val remoteD: RemoteDataInterface) : RepoXmlUrlConnectionInterface {
    override fun getData(): ModelData {
        remoteD.getData(XML_URI)
        //В основном потоке можно делать  полезную работу.
        remoteD.th.join() //Ждем пока придут данные
        return ModelData(remoteD.dataFromKiparo.data)   // Маппинг из модельки представленной в Data слое  в модельку представленная в слое domain
    }
}