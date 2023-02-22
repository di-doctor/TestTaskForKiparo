package domain.useCases

import domain.models.ModelData
import domain.repositories.RepoJsonOkHttpInterface

class GetJsonOkHttpUseCase(private val repo: RepoJsonOkHttpInterface) {
    fun execute(): ModelData{
        return repo.getData()
    }
}