package domain.useCases

import domain.models.ModelData
import domain.repositories.RepoXmlOkHttpInterface

class GetXmlOkHttpUseCase(private val repo:RepoXmlOkHttpInterface) {
    fun execute(): ModelData {
        return repo.getData()
    }
}