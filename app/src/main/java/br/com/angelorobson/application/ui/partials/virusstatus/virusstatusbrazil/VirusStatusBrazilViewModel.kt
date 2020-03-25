package br.com.angelorobson.application.ui.partials.virusstatus.virusstatusbrazil

import br.com.angelorobson.application.util.BaseViewModel
import br.com.angelorobson.application.util.EventLiveData
import br.com.angelorobson.domain.models.response.StateStatusResponse
import br.com.angelorobson.service.utils.UseCaseBaseCallback
import br.com.angelorobson.usecases.remote.virusstatus.GetVirusStatusBrazilUseCase

class VirusStatusBrazilViewModel(
    private val getVirusStatusBrazilUseCase: GetVirusStatusBrazilUseCase
) : BaseViewModel<List<StateStatusResponse>>() {

    fun getStatusVirusBrazil() {
        getVirusStatusBrazilUseCase.getVirusStatusBrazil(object :
            UseCaseBaseCallback.UseCaseCallback<List<StateStatusResponse>> {
            override fun onSuccess(response: List<StateStatusResponse>) {
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