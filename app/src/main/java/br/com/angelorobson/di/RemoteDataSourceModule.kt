package br.com.angelorobson.di

import br.com.angelorobson.service.remote.virusstatus.VirusStatusRemoteDataSource
import br.com.angelorobson.service.remote.virusstatus.VirusStatusRemoteDataSourceImpl
import org.koin.dsl.module

val remoteDataSourceModule = module {

    single<VirusStatusRemoteDataSource> {
        VirusStatusRemoteDataSourceImpl(get())
    }
}