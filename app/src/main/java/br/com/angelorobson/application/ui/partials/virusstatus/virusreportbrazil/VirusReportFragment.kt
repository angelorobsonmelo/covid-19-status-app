package br.com.angelorobson.application.ui.partials.virusstatus.virusreportbrazil

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.angelorobson.application.ui.partials.virusstatus.virusreportbrazil.adapter.VirusReportAdapter
import br.com.angelorobson.application.util.BindingFragment
import br.com.angelorobson.application.util.EventObserver
import br.com.angelorobson.covid19.R
import br.com.angelorobson.covid19.databinding.FragmentVirusReportBinding
import br.com.angelorobson.domain.models.response.VirusReportBrazil
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel


class VirusReportFragment : BindingFragment<FragmentVirusReportBinding>() {

    override fun getLayoutResId(): Int = R.layout.fragment_virus_report

    private val viewModel by viewModel<VirusReportViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpFragment()
    }

    private fun setUpFragment() {
        showToolbarWithoutDisplayArrowBack(getString(R.string.all_states))
        viewModel.getVirusReportBrazil()
        initObservers()
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getVirusReportBrazil()
        }
    }

    private fun initObservers() {
        viewModel.successObserver.observe(viewLifecycleOwner, EventObserver {
            setupRecyclerView(it)
        })

        viewModel.errorObserver.observe(viewLifecycleOwner, EventObserver {
            showAlertError(it)
        })

        viewModel.emptyObserver.observe(viewLifecycleOwner, EventObserver {
            Snackbar.make(requireView(), getString(R.string.no_data), Snackbar.LENGTH_SHORT).show()
        })

        viewModel.isLoadingEventObserver.observe(viewLifecycleOwner, EventObserver {
            binding.swipeRefreshLayout.isRefreshing = it
        })

    }

    private fun setupRecyclerView(it: List<VirusReportBrazil>) {
        binding.recyclerView.run {
            adapter = VirusReportAdapter(it)
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

}
