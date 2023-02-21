package data.repositorys

import data.network.JSON_URI
import data.network.RemoteDataInterface
import data.network.XML_URI
import domain.models.ModelData
import domain.repositorys.RepoXmlUrlConnectionInterface

class RepoXmlUrlConnectionImp(val remoteD: RemoteDataInterface) : RepoXmlUrlConnectionInterface {
    override fun getData(): ModelData {
        remoteD.getData(XML_URI)
        remoteD.th.join()
        return ModelData(remoteD.dataFromKiparo.data)
    }
}