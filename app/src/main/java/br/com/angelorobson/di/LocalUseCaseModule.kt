package br.com.angelorobson.di

import br.com.angelorobson.usecases.local.GetAllPhotosLocalUseCase
import br.com.angelorobson.usecases.local.InsertPhotosLocalUseCase
import br.com.angelorobson.usecases.local.RemovePhotosLocalUseCase
import org.koin.dsl.module

val localUseCaseModule = module {

    single {
        InsertPhotosLocalUseCase(get())
    }

    single {
        GetAllPhotosLocalUseCase(get())
    }

    single {
        RemovePhotosLocalUseCase(get())
    }
}