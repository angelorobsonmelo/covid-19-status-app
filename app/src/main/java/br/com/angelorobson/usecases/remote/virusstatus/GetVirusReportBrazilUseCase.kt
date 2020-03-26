package br.com.angelorobson.usecases.remote.virusstatus

import br.com.angelorobson.domain.models.response.VirusReportBrazil
import br.com.angelorobson.service.remote.virusstatus.VirusStatusRemoteDataSource
import br.com.angelorobson.service.utils.BaseRemoteDataSource
import br.com.angelorobson.service.utils.UseCaseBaseCallback

class GetVirusReportBrazilUseCase(private val remoteDataSource: VirusStatusRemoteDataSource) {

    fun getVirusStatusBrazil(callback: UseCaseBaseCallback.UseCaseCallback<List<VirusReportBrazil>>) {
        remoteDataSource.getVirusReportBrazil(object :
            BaseRemoteDataSource.RemoteDataSourceCallback<List<VirusReportBrazil>> {
            override fun onSuccess(response: List<VirusReportBrazil>) {
                if (response.isNotEmpty()) {
                    callback.onSuccess(response)
                    return
                }

                callback.onEmptyData()
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