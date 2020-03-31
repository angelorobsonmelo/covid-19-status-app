package br.com.angelorobson.usecases.remote.virusstatus

import br.com.angelorobson.domain.models.response.VirusReportCountry
import br.com.angelorobson.service.remote.virusstatus.VirusStatusRemoteDataSource
import br.com.angelorobson.service.utils.BaseRemoteDataSource
import br.com.angelorobson.service.utils.UseCaseBaseCallback

class GetVirusReportCountriesUseCase(private val remoteDataSource: VirusStatusRemoteDataSource) {

    fun getVirusStatusBrazil(callback: UseCaseBaseCallback.UseCaseCallback<List<VirusReportCountry>>) {
        remoteDataSource.getVirusReportCountries(object :
            BaseRemoteDataSource.RemoteDataSourceCallback<List<VirusReportCountry>> {
            override fun onSuccess(response: List<VirusReportCountry>) {
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