package br.com.angelorobson.di

import br.com.angelorobson.service.local.photo.PhotoLocalDataSource
import br.com.angelorobson.service.local.photo.PhotoLocalDataSourceImpl
import org.koin.dsl.module

val localDataSourceModule = module {

    single<PhotoLocalDataSource> {
        PhotoLocalDataSourceImpl(get())
    }

}