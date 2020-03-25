package br.com.angelorobson.service.utils

class BaseRemoteDataSource {

    interface RemoteDataSourceCallback<R> {
        fun onSuccess(response: R)
        fun onError(errorMessage: String)
        fun isLoading(isLoading: Boolean)
    }

    interface VoidRemoteDataSourceCallback {
        fun onSuccess()
        fun onError(errorMessage: String)
        fun onEmpty()
        fun isLoading(isLoading: Boolean)
    }
}