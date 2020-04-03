package br.com.angelorobson.application.ui.partials.virusstatus.virusbyregion

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import br.com.angelorobson.application.util.BindingFragment
import br.com.angelorobson.application.util.EventObserver
import br.com.angelorobson.application.util.extensions.getNumberFormat
import br.com.angelorobson.covid19.R
import br.com.angelorobson.covid19.databinding.FragmentVirusReportByRegionBinding
import br.com.angelorobson.domain.models.dto.StatesGraphDto
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.chart.common.listener.Event
import com.anychart.chart.common.listener.ListenersInterface
import com.anychart.charts.Pie
import com.anychart.enums.Align
import com.anychart.enums.LegendLayout
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_virus_report_by_region.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class VirusReportByRegionFragment : BindingFragment<FragmentVirusReportByRegionBinding>() {

    override fun getLayoutResId(): Int = R.layout.fragment_virus_report_by_region

    private val viewModel by viewModel<VirusReportByRegionViewModel>()

    private lateinit var anyChartView: AnyChartView
    private lateinit var tabLayout: TabLayout
    lateinit var pie: Pie

    private val TAB_CASES = 0
    private val TAB_FATALITIES = 1
    private var tabPositionSelected = TAB_CASES
    private lateinit var title: String
    private var isChartLoadedFirstTime = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showToolbarWithDisplayArrowBack(getString(R.string.brazil_regions))
        anyChartView = any_chart_view
        tabLayout = tab_layout
        title = getString(R.string.cases)
        pie = AnyChart.pie()

        createTabs()
        initTabSelectedListener()
        hideBottomNavigation()
        initObservers()
        initSwipeListener()
    }

    private fun initTabSelectedListener() {
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            @SuppressLint("StringFormatMatches")
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.position?.apply {
                    when (this) {
                        TAB_CASES -> {
                            tabPositionSelected = this
                            title = getString(R.string.cases)
                            viewModel.getVirusReportByRegionBrazil()
                        }
                        TAB_FATALITIES -> {
                            title = getString(R.string.fatalities)
                            tabPositionSelected = this
                            viewModel.getVirusReportByRegionBrazil()
                        }
                    }
                }
            }

        })

    }

    private fun createTabs() {
        tabLayout.addTab(
            tabLayout.newTab().setText(getString(R.string.cases))
        )

        tabLayout.addTab(
            tabLayout.newTab().setText(getString(R.string.fatalities))
        )

    }

    private fun initSwipeListener() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getVirusReportByRegionBrazil()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getVirusReportByRegionBrazil()
    }

    private fun initObservers() {
        viewModel.successObserver.observe(viewLifecycleOwner, EventObserver {
            setUpGraph(it)
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

    private fun setUpGraph(stateGraphs: List<StatesGraphDto>) {
        pie.setOnClickListener(object :
            ListenersInterface.OnClickListener(arrayOf("x", "value")) {
            override fun onClick(event: Event) {
            }
        })

        val data =
            if (tabPositionSelected == 0) getDataByTabSelectedCases(stateGraphs)
            else getDataByTabSelectedFatalities(stateGraphs)

        pie.data(data)
        pie.title(getString(R.string.case_by_region, title))
        pie.animation(true, 2000)
        pie.labels().position("outside")
        pie.legend().title().enabled(false)
        pie.legend()
            .position("center-bottom")
            .itemsLayout(LegendLayout.VERTICAL)
            .align(Align.LEFT)

        if (isChartLoadedFirstTime) {
            return
        }

        anyChartView.setChart(pie)
        isChartLoadedFirstTime = true
    }

    private fun getDataByTabSelectedCases(
        stateGraphs: List<StatesGraphDto>
    ): MutableList<DataEntry> {
        val data = arrayListOf<DataEntry>()

        stateGraphs.forEach {
            data.add(
                ValueDataEntry(
                    getString(
                        R.string.region_name_with_total,
                        getString(it.regionNameResourceId),
                        it.totalCases.getNumberFormat()
                    ),
                    it.totalCases
                )
            )
        }

        return data
    }

    private fun getDataByTabSelectedFatalities(
        stateGraphs: List<StatesGraphDto>
    ): MutableList<DataEntry> {
        val data = arrayListOf<DataEntry>()

        stateGraphs.forEach {
            data.add(
                ValueDataEntry(
                    getString(
                        R.string.region_name_with_total,
                        getString(it.regionNameResourceId),
                        it.totalDeaths.getNumberFormat()
                    ),
                    it.totalDeaths
                )
            )


        }

        return data
    }

}
