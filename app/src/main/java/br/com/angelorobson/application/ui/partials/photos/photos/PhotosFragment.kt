package br.com.angelorobson.application.ui.partials.photos.photos

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.angelorobson.R
import br.com.angelorobson.application.ui.partials.photos.photos.adapter.PhotosAdapter
import br.com.angelorobson.application.util.BindingFragment
import br.com.angelorobson.application.util.EventObserver
import br.com.angelorobson.application.util.extensions.isConnected
import br.com.angelorobson.databinding.PhotosFragmentBinding
import br.com.angelorobson.domain.Photo
import kotlinx.android.synthetic.main.photos_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class PhotosFragment : BindingFragment<PhotosFragmentBinding>() {

    private val viewModel by viewModel<PhotosViewModel>()

    override fun getLayoutResId(): Int = R.layout.photos_fragment


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()

        if (requireContext().isConnected) {
            viewModel.getPhotosFromApi()
            return
        }

        viewModel.getAllByLocalDatabase()
    }

    private fun initObservers() {
        viewModel.savePhotosObserver.observe(viewLifecycleOwner, EventObserver {
            print(it)
        })

        viewModel.removeAllPhotosObserver.observe(viewLifecycleOwner, EventObserver {
            print(it)
        })

        viewModel.successObserver.observe(viewLifecycleOwner, EventObserver {
            setupRecyclerView(it)
            viewModel.removeAll()
            viewModel.insert(it)
        })

        viewModel.errorObserver.observe(viewLifecycleOwner, EventObserver {
            print(it)
        })

        viewModel.isLoadingEventObserver.observe(viewLifecycleOwner, EventObserver {
            progressbar.visibility = if (it) View.VISIBLE else View.GONE
        })
    }

    private fun setupRecyclerView(it: List<Photo>) {
        with(binding.recyclerView) {
            adapter = PhotosAdapter(it) {

            }

            layoutManager = LinearLayoutManager(requireContext())
        }
    }

}
