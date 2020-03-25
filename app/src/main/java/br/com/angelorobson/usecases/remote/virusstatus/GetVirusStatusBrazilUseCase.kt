package br.com.angelorobson.usecases.remote.virusstatus

import br.com.angelorobson.covid19.R
import br.com.angelorobson.domain.models.response.StateStatusResponse
import br.com.angelorobson.service.remote.virusstatus.VirusStatusRemoteDataSource
import br.com.angelorobson.service.utils.BaseRemoteDataSource
import br.com.angelorobson.service.utils.UseCaseBaseCallback

class GetVirusStatusBrazilUseCase(private val remoteDataSource: VirusStatusRemoteDataSource) {

    fun getVirusStatusBrazil(callback: UseCaseBaseCallback.UseCaseCallback<List<StateStatusResponse>>) {
        remoteDataSource.getVirusStatusBrazil(object :
            BaseRemoteDataSource.RemoteDataSourceCallback<List<StateStatusResponse>> {
            override fun onSuccess(response: List<StateStatusResponse>) {
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