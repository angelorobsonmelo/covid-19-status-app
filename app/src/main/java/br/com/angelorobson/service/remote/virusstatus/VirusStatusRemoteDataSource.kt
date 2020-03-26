package br.com.angelorobson.service.remote.virusstatus

import br.com.angelorobson.domain.models.response.VirusReportBrazil
import br.com.angelorobson.domain.models.response.VirusStatusBrazil
import br.com.angelorobson.service.utils.BaseRemoteDataSource

interface VirusStatusRemoteDataSource {

    fun getVirusStatusBrazil(callback: BaseRemoteDataSource.RemoteDataSourceCallback<VirusStatusBrazil>)
    fun getVirusReportBrazil(callback: BaseRemoteDataSource.RemoteDataSourceCallback<List<VirusReportBrazil>>)
}