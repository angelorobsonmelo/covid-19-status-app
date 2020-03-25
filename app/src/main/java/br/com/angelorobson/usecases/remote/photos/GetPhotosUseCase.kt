package br.com.angelorobson.usecases.remote.photos


import br.com.angelorobson.domain.Photo
import br.com.angelorobson.service.remote.photo.PhotoRemoteDataSource
import br.com.angelorobson.service.utils.UseCaseBaseCallback
import br.com.angelorobson.service.utils.BaseRemoteDataSource


class GetPhotosUseCase(private val photoRemoteDataSource: PhotoRemoteDataSource) {

     fun getPosts(callback: UseCaseBaseCallback.UseCaseCallback<List<Photo>>) {
        photoRemoteDataSource.getPhotos(object :
            BaseRemoteDataSource.RemoteDataSourceCallback<List<Photo>> {
            override fun onSuccess(response: List<Photo>) {
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