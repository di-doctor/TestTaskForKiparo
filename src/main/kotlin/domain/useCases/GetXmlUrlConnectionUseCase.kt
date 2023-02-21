package domain.useCases

import domain.models.ModelData
import domain.repositorys.RepoXmlUrlConnectionInterface

class GetXmlUrlConnectionUseCase(private val repo:RepoXmlUrlConnectionInterface) {
    fun execute():ModelData{
        return repo.getData()
    }
}