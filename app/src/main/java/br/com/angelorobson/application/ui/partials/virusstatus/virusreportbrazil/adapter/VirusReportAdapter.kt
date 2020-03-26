package br.com.angelorobson.application.ui.partials.virusstatus.virusreportbrazil.adapter

import br.com.angelorobson.application.util.BindingAdapter
import br.com.angelorobson.covid19.R
import br.com.angelorobson.covid19.databinding.VirusStateItemBinding
import br.com.angelorobson.domain.models.response.VirusReportBrazil

class VirusReportAdapter(private val virusReports: List<VirusReportBrazil>) :
    BindingAdapter<VirusStateItemBinding>() {

    override fun getLayoutResId(): Int = R.layout.virus_state_item

    override fun onBindViewHolder(binding: VirusStateItemBinding, position: Int) {
        binding.run {
            virusReport = virusReports[position]
            executePendingBindings()
        }
    }

    override fun getItemCount(): Int = virusReports.size
}