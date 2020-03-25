package br.com.angelorobson.di

import br.com.angelorobson.usecases.remote.photos.ClearJobsUseCase
import br.com.angelorobson.usecases.remote.photos.GetPhotosUseCase
import org.koin.dsl.module

val remoteUseCaseModule = module {

    single {
        GetPhotosUseCase(get())
    }

    single {
        ClearJobsUseCase(get())
    }
}