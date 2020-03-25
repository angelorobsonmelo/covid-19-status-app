package br.com.angelorobson.application.ui.partials.photos.photos

import androidx.lifecycle.MutableLiveData
import br.com.angelorobson.application.util.BaseViewModel
import br.com.angelorobson.application.util.EventLiveData
import br.com.angelorobson.domain.Photo
import br.com.angelorobson.service.utils.UseCaseBaseCallback
import br.com.angelorobson.usecases.local.GetAllPhotosLocalUseCase
import br.com.angelorobson.usecases.local.InsertPhotosLocalUseCase
import br.com.angelorobson.usecases.local.RemovePhotosLocalUseCase
import br.com.angelorobson.usecases.remote.photos.ClearJobsUseCase
import br.com.angelorobson.usecases.remote.photos.GetPhotosUseCase

class PhotosViewModel(
    private val getPhotosUseCase: GetPhotosUseCase,
    private val clearJobsUseCase: ClearJobsUseCase,
    private val insertPhotosLocalUseCase: InsertPhotosLocalUseCase,
    private val removePhotosLocalUseCase: RemovePhotosLocalUseCase,
    private val getAllPhotosLocalUseCase: GetAllPhotosLocalUseCase
) : BaseViewModel<List<Photo>>() {

    val savePhotosObserver = MutableLiveData<EventLiveData<Boolean>>()
    val removeAllPhotosObserver = MutableLiveData<EventLiveData<Boolean>>()

    fun getPhotosFromApi() {
        getPhotosUseCase.getPosts(object :
            UseCaseBaseCallback.UseCaseCallback<List<Photo>> {

            override fun onSuccess(response: List<Photo>) {
                successObserver.value = EventLiveData(response)
            }

            override fun onEmptyData() {
                emptyObserver.value = EventLiveData(true)
            }

            override fun isLoading(isLoading: Boolean) {
                isLoadingEventObserver.value = EventLiveData(isLoading)
            }

            override fun onError(errorDescription: String) {
                errorObserver.value = EventLiveData(errorDescription)
            }
        })
    }

    override fun onCleared() {
        super.onCleared()
        clearJobsUseCase.clearJobs()
    }

    fun insert(photos: List<Photo>) {
        insertPhotosLocalUseCase.insert(
            photos,
            object : UseCaseBaseCallback.UseCaseCallback<Boolean> {
                override fun onSuccess(response: Boolean) {
                    savePhotosObserver.value = EventLiveData(true)
                }

                override fun onEmptyData() {

                }

                override fun isLoading(isLoading: Boolean) {
                    isLoadingEventObserver.value = EventLiveData(isLoading)
                }

                override fun onError(errorDescription: String) {
                    errorObserver.value = EventLiveData(errorDescription)
                }

            })
    }

    fun removeAll() {
        removePhotosLocalUseCase.remove(
            object : UseCaseBaseCallback.UseCaseCallback<Boolean> {
                override fun onSuccess(response: Boolean) {
                    removeAllPhotosObserver.value = EventLiveData(true)
                }

                override fun onEmptyData() {

                }

                override fun isLoading(isLoading: Boolean) {
                    isLoadingEventObserver.value = EventLiveData(isLoading)
                }

                override fun onError(errorDescription: String) {
                    errorObserver.value = EventLiveData(errorDescription)
                }

            })
    }

    fun getAllByLocalDatabase() {
        getAllPhotosLocalUseCase.getAll(
            object : UseCaseBaseCallback.UseCaseCallback<List<Photo>> {
                override fun onSuccess(response: List<Photo>) {
                    successObserver.value = EventLiveData(response)
                }

                override fun onEmptyData() {
                    emptyObserver.value = EventLiveData(true)
                }

                override fun isLoading(isLoading: Boolean) {
                    isLoadingEventObserver.value = EventLiveData(isLoading)
                }

                override fun onError(errorDescription: String) {
                    errorObserver.value = EventLiveData(errorDescription)
                }

            })
    }

}