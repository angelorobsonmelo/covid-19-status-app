package br.com.angelorobson.application.ui.partials.virusstatus.virusreportcountries

import br.com.angelorobson.application.util.BaseViewModel
import br.com.angelorobson.application.util.EventLiveData
import br.com.angelorobson.domain.models.response.VirusReportCountry
import br.com.angelorobson.service.utils.UseCaseBaseCallback
import br.com.angelorobson.usecases.remote.virusstatus.GetVirusReportCountriesUseCase

class VirusReportCountriesViewModel(private val reportCountriesUseCase: GetVirusReportCountriesUseCase) :
    BaseViewModel<List<VirusReportCountry>>() {

    fun getVirusReportCountries() {
        reportCountriesUseCase.getVirusStatusBrazil(object :
            UseCaseBaseCallback.UseCaseCallback<List<VirusReportCountry>> {
            override fun onSuccess(response: List<VirusReportCountry>) {
                successObserver.value = EventLiveData(response)
            }

            override fun onEmptyData() {
                emptyObserver.value = EventLiveData(true)
            }

            override fun isLoading(isLoading: Boolean) {
                isLoadingEventObserver.value = EventLiveData(isLoading)
            }

            override fun onError(errorDescription: String) {
                errorObserver.value = EventLiveData(errorDescription)
            }

        })
    }
}