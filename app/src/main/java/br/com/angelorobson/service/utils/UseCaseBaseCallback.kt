package br.com.angelorobson.service.utils

interface UseCaseBaseCallback {

    interface UseCaseCallback<R> {
        fun onSuccess(response: R)
        fun onEmptyData()
        fun isLoading(isLoading: Boolean)
        fun onError(errorDescription: String)
    }

    interface VoidUseCaseCallback {
        fun onSuccess()
        fun onEmptyData()
        fun isLoading(isLoading: Boolean)
        fun onError(errorDescription: String)
    }

}