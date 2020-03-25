package br.com.angelorobson.di

import br.com.angelorobson.service.remote.photo.PhotoRemoteDataSource
import br.com.angelorobson.service.remote.photo.PhotoRemoteDataSourceImpl
import br.com.angelorobson.service.remote.virusstatus.VirusStatusRemoteDataSource
import br.com.angelorobson.service.remote.virusstatus.VirusStatusRemoteDataSourceImpl
import org.koin.dsl.module

val remoteDataSourceModule = module {

    single<PhotoRemoteDataSource> {
        PhotoRemoteDataSourceImpl(get())
    }

    single<VirusStatusRemoteDataSource> {
        VirusStatusRemoteDataSourceImpl(get())
    }
}