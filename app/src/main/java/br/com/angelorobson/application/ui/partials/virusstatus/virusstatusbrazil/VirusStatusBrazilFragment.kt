package br.com.angelorobson.application.ui.partials.virusstatus.virusstatusbrazil

import android.os.Bundle
import android.view.View
import br.com.angelorobson.application.util.BindingFragment
import br.com.angelorobson.application.util.EventObserver
import br.com.angelorobson.covid19.R
import br.com.angelorobson.covid19.databinding.FragmentVirusStatusBrazilBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class VirusStatusBrazilFragment : BindingFragment<FragmentVirusStatusBrazilBinding>() {

    private val viewModel by viewModel<VirusStatusBrazilViewModel>()

    override fun getLayoutResId(): Int = R.layout.fragment_virus_status_brazil

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpFragment()
        initObservers()
    }

    private fun setUpFragment() {
        showToolbarWithoutDisplayArrowBack(getString(R.string.resume))
        viewModel.getStatusVirusBrazil()

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getStatusVirusBrazil()
        }
    }

    private fun initObservers() {
        viewModel.successObserver.observe(viewLifecycleOwner, EventObserver {
            binding.virusBrazil = it
        })

        viewModel.errorObserver.observe(viewLifecycleOwner, EventObserver {

        })

        viewModel.emptyObserver.observe(viewLifecycleOwner, EventObserver {

        })

        viewModel.isLoadingEventObserver.observe(viewLifecycleOwner, EventObserver {
            binding.swipeRefreshLayout.isRefreshing = it
            binding.tvUpdateAt.visibility = if (it) View.INVISIBLE else View.VISIBLE
        })

    }
}

