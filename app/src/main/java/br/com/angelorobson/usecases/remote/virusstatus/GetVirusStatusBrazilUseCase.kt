package br.com.angelorobson.usecases.remote.virusstatus

import br.com.angelorobson.domain.models.response.VirusStatusBrazil
import br.com.angelorobson.service.remote.virusstatus.VirusStatusRemoteDataSource
import br.com.angelorobson.service.utils.BaseRemoteDataSource
import br.com.angelorobson.service.utils.UseCaseBaseCallback

class GetVirusStatusBrazilUseCase(private val remoteDataSource: VirusStatusRemoteDataSource) {

    fun getVirusStatusBrazil(callback: UseCaseBaseCallback.UseCaseCallback<VirusStatusBrazil>) {
        remoteDataSource.getVirusStatusBrazil(object :
            BaseRemoteDataSource.RemoteDataSourceCallback<VirusStatusBrazil> {
            override fun onSuccess(response: VirusStatusBrazil) {
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