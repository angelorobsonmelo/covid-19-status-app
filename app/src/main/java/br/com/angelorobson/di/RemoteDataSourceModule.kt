package br.com.angelorobson.di

import br.com.angelorobson.service.remote.photo.PhotoRemoteDataSource
import br.com.angelorobson.service.remote.photo.PhotoRemoteDataSourceImpl
import org.koin.dsl.module

val remoteDataSourceModule = module {

    single<PhotoRemoteDataSource> {
        PhotoRemoteDataSourceImpl(get())
    }
}