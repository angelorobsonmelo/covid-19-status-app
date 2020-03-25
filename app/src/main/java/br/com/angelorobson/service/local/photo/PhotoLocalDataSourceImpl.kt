package br.com.angelorobson.service.local.photo

import br.com.angelorobson.domain.Photo
import br.com.angelorobson.service.utils.BaseRemoteDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PhotoLocalDataSourceImpl(private val dao: PhotoDao) :
    PhotoLocalDataSource {


    override fun insert(
        photos: List<Photo>,
        callback: BaseRemoteDataSource.RemoteDataSourceCallback<Boolean>
    ) {
        dao.insert(photos)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { callback.isLoading(true) }
            .doAfterTerminate { callback.isLoading(false) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    callback.onSuccess(true)
                }, {
                    callback.onError(it.localizedMessage)
                }
            )
    }

    override fun removeAll(callback: BaseRemoteDataSource.RemoteDataSourceCallback<Boolean>) {
        dao.removeAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { callback.isLoading(true) }
            .doAfterTerminate { callback.isLoading(false) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    callback.onSuccess(true)
                }, {
                    callback.onError(it.localizedMessage)
                }
            )
    }

    override fun getAll(callback: BaseRemoteDataSource.RemoteDataSourceCallback<List<Photo>>) {
        dao.getPhotos()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { callback.isLoading(true) }
            .doAfterTerminate { callback.isLoading(false) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    callback.onSuccess(it)
                }, {
                    callback.onError(it.localizedMessage)
                }
            )
    }
}