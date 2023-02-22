package domain.useCases

import domain.models.ModelData
import domain.repositories.RepoJsonUrlConnectionInterface


class GetJsonUrlConnectionUseCase(private val repo: RepoJsonUrlConnectionInterface) {
    fun execute(): ModelData {
        return repo.getData()
    }
}
