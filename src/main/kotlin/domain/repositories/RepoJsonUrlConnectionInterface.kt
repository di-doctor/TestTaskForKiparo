package domain.repositories

import domain.models.ModelData

interface RepoJsonUrlConnectionInterface {
    fun getData() : ModelData
}