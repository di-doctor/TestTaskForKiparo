package domain.repositorys

import domain.models.ModelData

interface RepoJsonOkHttpInterface {
    fun getData() : ModelData
}