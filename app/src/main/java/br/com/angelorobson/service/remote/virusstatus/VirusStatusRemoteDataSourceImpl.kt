package br.com.angelorobson.service.remote.virusstatus

import br.com.angelorobson.domain.models.response.StateStatusResponse
import br.com.angelorobson.service.utils.BaseRemoteDataSource
import br.com.angelorobson.service.utils.CoroutineScopeContext
import kotlinx.coroutines.launch

class VirusStatusRemoteDataSourceImpl(private val virusStatusApiDataSource: VirusStatusApiDataSource) :
    VirusStatusRemoteDataSource, CoroutineScopeContext() {


    override fun getVirusStatusBrazil(callback: BaseRemoteDataSource.RemoteDataSourceCallback<List<StateStatusResponse>>) {
        launch {
            callback.isLoading(true)
            try {
                val result = virusStatusApiDataSource.getVirusStatusBrazil().await()
                callback.onSuccess(result.data)
            } catch (t: Throwable) {
                callback.onError(t.localizedMessage)
            } finally {
                callback.isLoading(false)
            }
        }
    }


}