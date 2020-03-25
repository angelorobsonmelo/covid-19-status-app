package br.com.angelorobson.usecases.local

import br.com.angelorobson.service.local.photo.PhotoLocalDataSource
import br.com.angelorobson.service.utils.BaseRemoteDataSource
import br.com.angelorobson.service.utils.UseCaseBaseCallback


class RemovePhotosLocalUseCase(private val photoLocalDataSource: PhotoLocalDataSource) {

    fun remove(callback: UseCaseBaseCallback.UseCaseCallback<Boolean>) {
        photoLocalDataSource.removeAll(object :
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