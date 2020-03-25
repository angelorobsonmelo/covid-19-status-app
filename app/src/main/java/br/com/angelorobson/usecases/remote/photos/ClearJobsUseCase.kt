package br.com.angelorobson.usecases.remote.photos

import br.com.angelorobson.service.remote.photo.PhotoRemoteDataSource

class ClearJobsUseCase(private val photoRemoteDataSource: PhotoRemoteDataSource) {

    fun clearJobs() {
        photoRemoteDataSource.clearJobs()
    }
}