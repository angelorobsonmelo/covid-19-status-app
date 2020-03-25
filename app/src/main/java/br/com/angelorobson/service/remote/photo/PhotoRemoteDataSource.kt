package br.com.angelorobson.service.remote.photo

import br.com.angelorobson.domain.Photo
import br.com.angelorobson.service.utils.RemoteDataSourceClearJobs
import br.com.angelorobson.service.utils.BaseRemoteDataSource

interface PhotoRemoteDataSource : RemoteDataSourceClearJobs {

    fun getPhotos(callback: BaseRemoteDataSource.RemoteDataSourceCallback<List<Photo>>)
}