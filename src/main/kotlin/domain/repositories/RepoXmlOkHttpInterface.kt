package domain.repositories

import domain.models.ModelData

interface RepoXmlOkHttpInterface {
    fun getData() : ModelData
}