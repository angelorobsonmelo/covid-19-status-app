package br.com.angelorobson.di

import br.com.angelorobson.service.remote.photo.PhotoApiDataSource
import br.com.angelorobson.service.remote.virusstatus.VirusStatusApiDataSource
import org.koin.dsl.module

val apiModule = module {

    single { createApi<PhotoApiDataSource>(get()) }
    single { createApi<VirusStatusApiDataSource>(get()) }

}