package br.com.angelorobson.di

import br.com.angelorobson.usecases.remote.virusstatus.GetVirusReportBrazilUseCase
import br.com.angelorobson.usecases.remote.virusstatus.GetVirusReportCountriesUseCase
import br.com.angelorobson.usecases.remote.virusstatus.GetVirusStatusBrazilUseCase
import org.koin.dsl.module

val remoteUseCaseModule = module {

    single {
        GetVirusStatusBrazilUseCase(get())
    }

    single {
        GetVirusReportBrazilUseCase(get())
    }

    single {
        GetVirusReportCountriesUseCase(get())
    }
}