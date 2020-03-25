package br.com.angelorobson.service.remote.virusstatus

import br.com.angelorobson.domain.models.response.ResponseListBase
import br.com.angelorobson.domain.models.response.StateStatusResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface VirusStatusApiDataSource {

    @GET("v1")
    fun getVirusStatusBrazil(): Deferred<ResponseListBase<StateStatusResponse>>

}