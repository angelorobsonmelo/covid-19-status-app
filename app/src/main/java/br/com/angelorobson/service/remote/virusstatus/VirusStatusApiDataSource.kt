package br.com.angelorobson.service.remote.virusstatus

import br.com.angelorobson.domain.models.response.ResponseBase
import br.com.angelorobson.domain.models.response.StateStatusResponse
import br.com.angelorobson.domain.models.response.VirusStatusBrazil
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface VirusStatusApiDataSource {

    @GET("api/report/v1/brazil")
    fun getVirusStatusBrazil(): Deferred<ResponseBase<VirusStatusBrazil>>

}