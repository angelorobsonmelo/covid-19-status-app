package br.com.angelorobson.application.ui.partials.virusstatus.virusreportcountries.adapter

import android.widget.Filter
import android.widget.Filterable
import br.com.angelorobson.application.util.BindingAdapter
import br.com.angelorobson.covid19.R
import br.com.angelorobson.covid19.databinding.VirusReportCountryItemBinding
import br.com.angelorobson.domain.models.response.VirusReportCountry
import java.util.*
import kotlin.collections.ArrayList

class VirusReportCountriesAdapter(private val virusReports: MutableList<VirusReportCountry>) :
    BindingAdapter<VirusReportCountryItemBinding>(), Filterable {

    private var virusFull: List<VirusReportCountry> = ArrayList(virusReports)

    override fun getLayoutResId(): Int = R.layout.virus_report_country_item

    override fun onBindViewHolder(binding: VirusReportCountryItemBinding, position: Int) {
        binding.run {
            virusReport = virusReports[position]
            executePendingBindings()
        }
    }

    override fun getItemCount(): Int = virusReports.size

    fun updateItems(it: List<VirusReportCountry>) {
        virusReports.clear()
        virusReports.addAll(it)
        virusFull = ArrayList(it)
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter = filteredObjects

    private val filteredObjects = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList = ArrayList<VirusReportCountry>()

            if (constraint == null || constraint.isEmpty()) {
                filteredList.addAll(virusFull)
            } else {
                val filterPattern =
                    constraint.toString().toLowerCase(Locale("pt", "BR")).trim { it <= ' ' }

                for (item in virusFull) {
                    if (item.country.toLowerCase(Locale("pt", "BR")).contains(filterPattern)) {
                        filteredList.add(item)
                    }
                }
            }

            val results = FilterResults()
            results.values = filteredList

            return results
        }

        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            virusReports.clear()
            virusReports.addAll(results.values as List<VirusReportCountry>)
            notifyDataSetChanged()
        }
    }


}