package br.com.angelorobson.application.ui.partials.virusstatus.virusreportbrazil

import br.com.angelorobson.application.util.BaseViewModel
import br.com.angelorobson.application.util.EventLiveData
import br.com.angelorobson.domain.models.response.VirusReportBrazil
import br.com.angelorobson.service.utils.UseCaseBaseCallback
import br.com.angelorobson.usecases.remote.virusstatus.GetVirusReportBrazilUseCase

class VirusReportViewModel(private val getVirusReportBrazilUseCase: GetVirusReportBrazilUseCase) :
    BaseViewModel<List<VirusReportBrazil>>() {

    fun getVirusReportBrazil() {
        getVirusReportBrazilUseCase.getVirusStatusBrazil(object :
            UseCaseBaseCallback.UseCaseCallback<List<VirusReportBrazil>> {
            override fun onSuccess(response: List<VirusReportBrazil>) {
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