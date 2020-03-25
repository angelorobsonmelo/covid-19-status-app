package br.com.angelorobson.service.local.photo

import br.com.angelorobson.domain.Photo
import br.com.angelorobson.service.utils.BaseRemoteDataSource

interface PhotoLocalDataSource {

    fun insert(
        photos: List<Photo>,
        callback: BaseRemoteDataSource.RemoteDataSourceCallback<Boolean>
    )

    fun removeAll(callback: BaseRemoteDataSource.RemoteDataSourceCallback<Boolean>)

    fun getAll(callback: BaseRemoteDataSource.RemoteDataSourceCallback<List<Photo>>)
}