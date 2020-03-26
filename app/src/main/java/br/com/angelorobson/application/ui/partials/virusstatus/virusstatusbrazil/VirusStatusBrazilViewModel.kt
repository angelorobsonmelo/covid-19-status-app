package br.com.angelorobson.application.ui.partials.virusstatus.virusstatusbrazil

import br.com.angelorobson.application.util.BaseViewModel
import br.com.angelorobson.application.util.EventLiveData
import br.com.angelorobson.domain.models.response.VirusStatusBrazil
import br.com.angelorobson.service.utils.UseCaseBaseCallback
import br.com.angelorobson.usecases.remote.virusstatus.GetVirusStatusBrazilUseCase

class VirusStatusBrazilViewModel(
    private val getVirusStatusBrazilUseCase: GetVirusStatusBrazilUseCase
) : BaseViewModel<VirusStatusBrazil>() {

    fun getStatusVirusBrazil() {
        getVirusStatusBrazilUseCase.getVirusStatusBrazil(object :
            UseCaseBaseCallback.UseCaseCallback<VirusStatusBrazil> {
            override fun onSuccess(response: VirusStatusBrazil) {
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