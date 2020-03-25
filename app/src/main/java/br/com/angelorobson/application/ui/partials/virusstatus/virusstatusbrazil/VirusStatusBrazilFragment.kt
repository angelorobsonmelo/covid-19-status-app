package br.com.angelorobson.application.ui.partials.virusstatus.virusstatusbrazil

import android.os.Bundle
import android.view.View
import br.com.angelorobson.application.util.BindingFragment
import br.com.angelorobson.covid19.R
import br.com.angelorobson.covid19.databinding.FragmentVirusStatusBrazilBinding


class VirusStatusBrazilFragment : BindingFragment<FragmentVirusStatusBrazilBinding>() {


    override fun getLayoutResId(): Int = R.layout.fragment_virus_status_brazil

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}

