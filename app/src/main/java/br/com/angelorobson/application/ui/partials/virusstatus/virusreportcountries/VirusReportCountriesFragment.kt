package br.com.angelorobson.application.ui.partials.virusstatus.virusreportcountries

import android.os.Bundle
import android.text.InputType
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.angelorobson.application.ui.partials.virusstatus.virusreportcountries.adapter.VirusReportCountriesAdapter
import br.com.angelorobson.application.util.BindingFragment
import br.com.angelorobson.application.util.EventObserver
import br.com.angelorobson.covid19.R
import br.com.angelorobson.covid19.databinding.FragmentVirusReportCountriesBinding
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel


class VirusReportCountriesFragment : BindingFragment<FragmentVirusReportCountriesBinding>() {

    override fun getLayoutResId(): Int = R.layout.fragment_virus_report_countries

    private val viewModel by viewModel<VirusReportCountriesViewModel>()

    private lateinit var mAdapter: VirusReportCountriesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpFragment()
    }

    private fun setUpFragment() {
        showToolbarWithoutDisplayArrowBack(getString(R.string.world))
        setupRecyclerView()
        setHasOptionsMenu(true)

        viewModel.getVirusReportCountries()
        initObservers()
        initSwipeListener()
    }

    private fun initSwipeListener() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getVirusReportCountries()
        }
    }

    private fun setupRecyclerView() {
        mAdapter = VirusReportCountriesAdapter(mutableListOf())

        binding.recyclerView.run {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun initObservers() {
        viewModel.successObserver.observe(viewLifecycleOwner, EventObserver {
            mAdapter.updateItems(it)
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu_filter_country, menu)
        val searchView = setupSearchView(menu)
        setQueryTextListener(searchView)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun setupSearchView(menu: Menu): SearchView {
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.imeOptions = EditorInfo.IME_ACTION_DONE
        searchView.inputType = InputType.TYPE_CLASS_TEXT
        return searchView
    }

    private fun setQueryTextListener(searchView: SearchView) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                mAdapter.filter.filter(newText)
                return false
            }
        })
    }

}
