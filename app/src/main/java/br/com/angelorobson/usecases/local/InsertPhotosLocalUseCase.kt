package br.com.angelorobson.usecases.local

import br.com.angelorobson.domain.Photo
import br.com.angelorobson.service.local.photo.PhotoLocalDataSource
import br.com.angelorobson.service.utils.BaseRemoteDataSource
import br.com.angelorobson.service.utils.UseCaseBaseCallback

class InsertPhotosLocalUseCase(private val photoLocalDataSource: PhotoLocalDataSource) {

    fun insert(photos: List<Photo>, callback: UseCaseBaseCallback.UseCaseCallback<Boolean>) {
        photoLocalDataSource.insert(photos, object :
            BaseRemoteDataSource.RemoteDataSourceCallback<Boolean> {
            override fun onSuccess(response: Boolean) {
                callback.onSuccess(response)
            }

            override fun onError(errorMessage: String) {
                callback.onError(errorMessage)
            }

            override fun isLoading(isLoading: Boolean) {
                callback.isLoading(isLoading)
            }

        })
    }


}