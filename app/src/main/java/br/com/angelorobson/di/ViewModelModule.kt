package br.com.angelorobson.di

import br.com.angelorobson.application.ui.partials.photos.photos.PhotosViewModel
import br.com.angelorobson.usecases.remote.photos.ClearJobsUseCase
import br.com.angelorobson.usecases.remote.photos.GetPhotosUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModuleModule = module {

    viewModel {
        PhotosViewModel(get(), get(), get(), get(), get())
    }
}