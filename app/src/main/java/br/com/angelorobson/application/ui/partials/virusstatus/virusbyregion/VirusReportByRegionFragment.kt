package br.com.angelorobson.application.ui.partials.virusstatus.virusbyregion

import android.os.Bundle
import android.view.View
import android.widget.Toast
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
import com.anychart.enums.Align
import com.anychart.enums.LegendLayout
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_virus_report_by_region.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class VirusReportByRegionFragment : BindingFragment<FragmentVirusReportByRegionBinding>() {

    override fun getLayoutResId(): Int = R.layout.fragment_virus_report_by_region

    private val viewModel by viewModel<VirusReportByRegionViewModel>()

    private lateinit var anyChartView: AnyChartView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showToolbarWithDisplayArrowBack(getString(R.string.brazil_regions))
        anyChartView = any_chart_view

        hideBottomNavigation()
        initObservers()
        initSwipeListener()
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
        val pie = AnyChart.pie()

        pie.setOnClickListener(object :
            ListenersInterface.OnClickListener(arrayOf("x", "value")) {
            override fun onClick(event: Event) {
                Toast.makeText(
                    requireContext(),
                    event.data["x"].toString() + ":" + event.data["value"],
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        val data: MutableList<DataEntry> = ArrayList()
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

        pie.data(data)

        pie.title("Fruits imported in 2015 (in kg)")
        pie.animation(true, 20000)

        pie.labels().position("outside")

        pie.legend().title().enabled(false)

        pie.legend()
            .position("center-bottom")
            .itemsLayout(LegendLayout.VERTICAL)
            .align(Align.LEFT)

        anyChartView.setChart(pie)
    }

}
