package br.com.angelorobson.application.util

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel<T> : ViewModel() {

    val successObserver = MutableLiveData<EventLiveData<T>>()
    var emptyObserver = MutableLiveData<EventLiveData<Boolean>>()
    val errorObserver = MutableLiveData<EventLiveData<String>>()
    val isLoadingObserver = MutableLiveData<Boolean>()
    val isLoadingEventObserver = MutableLiveData<EventLiveData<Boolean>>()

    fun loadingStarted() {
        isLoadingObserver.value = true
    }

    fun loadingFinished() {
        isLoadingObserver.value = false
    }

}