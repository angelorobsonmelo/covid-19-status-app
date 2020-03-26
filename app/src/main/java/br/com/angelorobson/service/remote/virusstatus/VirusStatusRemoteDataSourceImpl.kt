package br.com.angelorobson.service.remote.virusstatus

import br.com.angelorobson.domain.models.response.VirusReportBrazil
import br.com.angelorobson.domain.models.response.VirusStatusBrazil
import br.com.angelorobson.service.utils.BaseRemoteDataSource
import br.com.angelorobson.service.utils.CoroutineScopeContext
import kotlinx.coroutines.launch

class VirusStatusRemoteDataSourceImpl(private val virusStatusApiDataSource: VirusStatusApiDataSource) :
    VirusStatusRemoteDataSource, CoroutineScopeContext() {


    override fun getVirusStatusBrazil(callback: BaseRemoteDataSource.RemoteDataSourceCallback<VirusStatusBrazil>) {
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

    override fun getVirusReportBrazil(callback: BaseRemoteDataSource.RemoteDataSourceCallback<List<VirusReportBrazil>>) {
        launch {
            callback.isLoading(true)
            try {
                val result = virusStatusApiDataSource.getReportBrazil().await()
                callback.onSuccess(result.data)
            } catch (t: Throwable) {
                callback.onError(t.localizedMessage)
            } finally {
                callback.isLoading(false)
            }
        }
    }


}