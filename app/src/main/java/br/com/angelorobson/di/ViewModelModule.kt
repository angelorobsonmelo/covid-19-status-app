package br.com.angelorobson.di

import br.com.angelorobson.application.ui.partials.virusstatus.virusbyregion.VirusReportByRegionFragment
import br.com.angelorobson.application.ui.partials.virusstatus.virusbyregion.VirusReportByRegionViewModel
import br.com.angelorobson.application.ui.partials.virusstatus.virusreportbrazil.VirusReportViewModel
import br.com.angelorobson.application.ui.partials.virusstatus.virusreportcountries.VirusReportCountriesViewModel
import br.com.angelorobson.application.ui.partials.virusstatus.virusstatusbrazil.VirusStatusBrazilViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModuleModule = module {

    viewModel {
        VirusStatusBrazilViewModel(get())
    }

    viewModel {
        VirusReportViewModel(get())
    }

    viewModel {
        VirusReportCountriesViewModel(get())
    }

    viewModel {
        VirusReportByRegionViewModel(get())
    }
}