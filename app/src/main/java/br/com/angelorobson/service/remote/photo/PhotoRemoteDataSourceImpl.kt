package br.com.angelorobson.service.remote.photo

import br.com.angelorobson.domain.Photo
import br.com.angelorobson.service.utils.BaseRemoteDataSource
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class PhotoRemoteDataSourceImpl(private val apiDataSource: PhotoApiDataSource) :
    PhotoRemoteDataSource, CoroutineScope {

    private var job: Job = Job()

    override fun getPhotos(callback: BaseRemoteDataSource.RemoteDataSourceCallback<List<Photo>>) {
        launch {
            callback.isLoading(true)
            try {
                val result = apiDataSource.getPhotos().await()
                onResult(result, callback)
            } catch (t: Throwable) {
                callback.onError(t.localizedMessage)
            } finally {
                callback.isLoading(false)
            }
        }
    }

    fun onResult(
        list: List<Photo>,
        callback: BaseRemoteDataSource.RemoteDataSourceCallback<List<Photo>>
    ) {
        callback.onSuccess(list)
    }

    override fun clearJobs() {
        job.cancel()
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

}