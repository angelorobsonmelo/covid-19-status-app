package br.com.angelorobson.service.remote.virusstatus

import br.com.angelorobson.domain.models.response.ResponseBase
import br.com.angelorobson.domain.models.response.VirusReportBrazil
import br.com.angelorobson.domain.models.response.VirusReportCountry
import br.com.angelorobson.domain.models.response.VirusStatusBrazil
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface VirusStatusApiDataSource {

    @GET("api/report/v1/brazil")
    fun getVirusStatusBrazil(): Deferred<ResponseBase<VirusStatusBrazil>>

    @GET("api/report/v1")
    fun getReportBrazil(): Deferred<ResponseBase<List<VirusReportBrazil>>>

    @GET("api/report/v1/countries")
    fun getReportCountries(): Deferred<ResponseBase<List<VirusReportCountry>>>

}