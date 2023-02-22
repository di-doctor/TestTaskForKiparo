package domain.repositories

import domain.models.ModelData

interface RepoJsonOkHttpInterface {
    fun getData() : ModelData
}