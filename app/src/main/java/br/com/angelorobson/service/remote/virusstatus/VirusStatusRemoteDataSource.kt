package br.com.angelorobson.service.remote.virusstatus

import br.com.angelorobson.domain.models.response.StateStatusResponse
import br.com.angelorobson.service.utils.BaseRemoteDataSource

interface VirusStatusRemoteDataSource {

    fun getVirusStatusBrazil(callback: BaseRemoteDataSource.RemoteDataSourceCallback<List<StateStatusResponse>>)
}