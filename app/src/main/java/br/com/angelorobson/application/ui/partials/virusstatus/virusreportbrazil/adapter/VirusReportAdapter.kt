package br.com.angelorobson.application.ui.partials.virusstatus.virusreportbrazil.adapter

import android.widget.Filter
import android.widget.Filterable
import br.com.angelorobson.application.util.BindingAdapter
import br.com.angelorobson.covid19.R
import br.com.angelorobson.covid19.databinding.VirusStateItemBinding
import br.com.angelorobson.domain.models.response.VirusReportBrazil
import java.util.*
import kotlin.collections.ArrayList

class VirusReportAdapter(private val virusReports: MutableList<VirusReportBrazil>) :
    BindingAdapter<VirusStateItemBinding>(), Filterable {

    private var virusFull: List<VirusReportBrazil> = ArrayList(virusReports)

    override fun getLayoutResId(): Int = R.layout.virus_state_item

    override fun onBindViewHolder(binding: VirusStateItemBinding, position: Int) {
        binding.run {
            virusReport = virusReports[position]
            executePendingBindings()
        }
    }

    override fun getItemCount(): Int = virusReports.size

    fun updateItems(it: List<VirusReportBrazil>) {
        virusReports.clear()
        virusReports.addAll(it)
        virusFull = ArrayList(it)
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter = filteredObjects

    private val filteredObjects = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList = ArrayList<VirusReportBrazil>()

            if (constraint == null || constraint.isEmpty()) {
                filteredList.addAll(virusFull)
            } else {
                val filterPattern =
                    constraint.toString().toLowerCase(Locale("PT", "br")).trim { it <= ' ' }

                for (item in virusFull) {
                    if (item.state.toLowerCase(Locale("PT", "br")).contains(filterPattern)) {
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
            virusReports.addAll(results.values as List<VirusReportBrazil>)
            notifyDataSetChanged()
        }
    }


}