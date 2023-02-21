package domain.repositorys

import domain.models.ModelData

interface RepoJsonUrlConnectionInterface {
    fun getData() : ModelData
}