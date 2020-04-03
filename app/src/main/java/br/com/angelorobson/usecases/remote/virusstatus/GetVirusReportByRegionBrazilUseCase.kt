package br.com.angelorobson.usecases.remote.virusstatus

import br.com.angelorobson.domain.models.response.VirusReportBrazil
import br.com.angelorobson.service.remote.virusstatus.VirusStatusRemoteDataSource
import br.com.angelorobson.service.utils.BaseRemoteDataSource
import br.com.angelorobson.service.utils.UseCaseBaseCallback
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.kotlin.toObservable

class GetVirusReportByRegionBrazilUseCase(private val remoteDataSource: VirusStatusRemoteDataSource) {

    fun getVirusStatusBrazil(callback: UseCaseBaseCallback.UseCaseCallback<List<VirusReportBrazil>>) {
        remoteDataSource.getVirusReportBrazil(object :
            BaseRemoteDataSource.RemoteDataSourceCallback<List<VirusReportBrazil>> {
            override fun onSuccess(response: List<VirusReportBrazil>) {
                getStatesByRegion(response)
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

    private fun getStatesByRegion(response: List<VirusReportBrazil>) {
        handleNorthStates(response)
        handleNorthStates(response)
        handleMidwestStates(response)
        handleSoutheastStates(response)
        handleSouthStates(response)
    }

    private fun handleNorthStates(response: List<VirusReportBrazil>) {
        val statesFiltered = ArrayList<VirusReportBrazil>()
        response.toObservable()
            .filter {
                it.uf == "AC" ||
                        it.uf == "AP" ||
                        it.uf == "AM" ||
                        it.uf == "PA" ||
                        it.uf == "RO" ||
                        it.uf == "RR" ||
                        it.uf == "TO"
            }
            .subscribeBy(
                onNext = {
                    statesFiltered.add(it)
                },
                onError = { print(it) },
                onComplete = {

                }
            )
    }

    private fun handleNortheastStates(response: List<VirusReportBrazil>) {
        val statesFiltered = ArrayList<VirusReportBrazil>()
        response.toObservable()
            .filter {
                it.uf == "MA" ||
                        it.uf == "PI" ||
                        it.uf == "CE" ||
                        it.uf == "RN" ||
                        it.uf == "PE" ||
                        it.uf == "PB" ||
                        it.uf == "SE" ||
                        it.uf == "AL" ||
                        it.uf == "BA"
            }
            .subscribeBy(
                onNext = {
                    statesFiltered.add(it)
                },
                onError = { print(it) },
                onComplete = {

                }
            )
    }

    private fun handleMidwestStates(response: List<VirusReportBrazil>) {
        val statesFiltered = ArrayList<VirusReportBrazil>()
        response.toObservable()
            .filter {
                it.uf == "MT" ||
                        it.uf == "MS" ||
                        it.uf == "GO"
            }
            .subscribeBy(
                onNext = {
                    statesFiltered.add(it)
                },
                onError = { print(it) },
                onComplete = {

                }
            )
    }

    private fun handleSoutheastStates(response: List<VirusReportBrazil>) {
        val statesFiltered = ArrayList<VirusReportBrazil>()
        response.toObservable()
            .filter {
                it.uf == "SP" ||
                        it.uf == "RJ" ||
                        it.uf == "ES" ||
                        it.uf == "MG"
            }
            .subscribeBy(
                onNext = {
                    statesFiltered.add(it)
                },
                onError = { print(it) },
                onComplete = {

                }
            )
    }

    private fun handleSouthStates(response: List<VirusReportBrazil>) {
        val statesFiltered = ArrayList<VirusReportBrazil>()
        response.toObservable()
            .filter {
                it.uf == "PR" ||
                        it.uf == "RS" ||
                        it.uf == "SC"
            }
            .subscribeBy(
                onNext = {
                    statesFiltered.add(it)
                },
                onError = { print(it) },
                onComplete = {

                }
            )
    }


}