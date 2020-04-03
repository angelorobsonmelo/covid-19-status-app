package br.com.angelorobson.usecases.remote.virusstatus

import android.annotation.SuppressLint
import br.com.angelorobson.covid19.R
import br.com.angelorobson.domain.models.dto.StatesGraphDto
import br.com.angelorobson.domain.models.response.VirusReportBrazil
import br.com.angelorobson.service.remote.virusstatus.VirusStatusRemoteDataSource
import br.com.angelorobson.service.utils.BaseRemoteDataSource
import br.com.angelorobson.service.utils.UseCaseBaseCallback
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function5
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.kotlin.toObservable
import io.reactivex.schedulers.Schedulers

class GetVirusReportByRegionBrazilUseCase(private val remoteDataSource: VirusStatusRemoteDataSource) {

    private var responseStatesList = listOf<VirusReportBrazil>()

    fun getVirusStatusByRegionBrazil(callback: UseCaseBaseCallback.UseCaseCallback<List<StatesGraphDto>>) {
        remoteDataSource.getVirusReportBrazil(object :
            BaseRemoteDataSource.RemoteDataSourceCallback<List<VirusReportBrazil>> {
            override fun onSuccess(response: List<VirusReportBrazil>) {
                if (response.isNotEmpty()) {
                    responseStatesList = response
                    handleStatesByRegion(callback)
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

    @SuppressLint("CheckResult")
    private fun handleStatesByRegion(
        callback: UseCaseBaseCallback.UseCaseCallback<List<StatesGraphDto>>
    ) {
        Observable.zip(
            handleNorthStates(),
            handleNortheastStates(),
            handleMidwestStates(),
            handleSoutheastStates(),
            handleSouthStates(),
            Function5 { northStates: ArrayList<VirusReportBrazil>,
                        northeastStates: ArrayList<VirusReportBrazil>,
                        midwestStates: ArrayList<VirusReportBrazil>,
                        southeastStates: ArrayList<VirusReportBrazil>,
                        southStates: ArrayList<VirusReportBrazil> ->

                val northStatesGraphDto = getStatesGraph(R.string.north, northStates)
                val northeastStatesGraphDto = getStatesGraph(R.string.northeast, northeastStates)
                val midwestStatesGraphDto = getStatesGraph(R.string.midwest, midwestStates)
                val southeastStatesGraphDto = getStatesGraph(R.string.southeast, southeastStates)
                val southStatesGraphDto = getStatesGraph(R.string.south, southStates)

                listOf(
                    northStatesGraphDto,
                    northeastStatesGraphDto,
                    midwestStatesGraphDto,
                    southeastStatesGraphDto,
                    southStatesGraphDto
                )

            })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { callback.isLoading(true) }
            .doAfterTerminate { callback.isLoading(false) }
            .subscribe(
                {
                    callback.onSuccess(it)
                },
                {
                    print(it)
                }
            )
    }

    private fun getStatesGraph(
        regionResourceId: Int,
        states: ArrayList<VirusReportBrazil>
    ): StatesGraphDto {
        val totalCasesNorth = states.map {
            it.cases
        }.reduce { acc, i ->
            acc + i
        }

        val totalDeathsNorth = states.map {
            it.deaths
        }.reduce { acc, i ->
            acc + i
        }

        return StatesGraphDto(
            regionResourceId,
            totalCasesNorth,
            totalDeathsNorth
        )
    }

    private fun handleNorthStates(): Observable<ArrayList<VirusReportBrazil>> {
        val statesFiltered = ArrayList<VirusReportBrazil>()
        responseStatesList.toObservable()
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

        return Observable.just(statesFiltered)
    }

    private fun handleNortheastStates(): Observable<ArrayList<VirusReportBrazil>> {
        val statesFiltered = ArrayList<VirusReportBrazil>()
        responseStatesList.toObservable()
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

        return Observable.just(statesFiltered)
    }

    private fun handleMidwestStates(): Observable<ArrayList<VirusReportBrazil>> {
        val statesFiltered = ArrayList<VirusReportBrazil>()
        responseStatesList.toObservable()
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

        return Observable.just(statesFiltered)
    }

    private fun handleSoutheastStates(): Observable<ArrayList<VirusReportBrazil>> {
        val statesFiltered = ArrayList<VirusReportBrazil>()
        responseStatesList.toObservable()
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

        return Observable.just(statesFiltered)
    }

    private fun handleSouthStates(): Observable<ArrayList<VirusReportBrazil>> {
        val statesFiltered = ArrayList<VirusReportBrazil>()
        responseStatesList.toObservable()
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

        return Observable.just(statesFiltered)
    }


}