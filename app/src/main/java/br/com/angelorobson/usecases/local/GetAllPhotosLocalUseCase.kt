package br.com.angelorobson.usecases.local

import br.com.angelorobson.domain.Photo
import br.com.angelorobson.service.local.photo.PhotoLocalDataSource
import br.com.angelorobson.service.utils.BaseRemoteDataSource
import br.com.angelorobson.service.utils.UseCaseBaseCallback

class GetAllPhotosLocalUseCase(private val photoLocalDataSource: PhotoLocalDataSource) {

    fun getAll(callback: UseCaseBaseCallback.UseCaseCallback<List<Photo>>) {
        photoLocalDataSource.getAll(object :
            BaseRemoteDataSource.RemoteDataSourceCallback<List<Photo>> {
            override fun onSuccess(response: List<Photo>) {
                if (response.isEmpty()) {
                    callback.onEmptyData()
                    return
                }
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