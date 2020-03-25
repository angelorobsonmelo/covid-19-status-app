package br.com.angelorobson.service.remote.photo

import br.com.angelorobson.domain.Photo
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface PhotoApiDataSource {

    @GET("albums/1/photos")
    fun getPhotos(): Deferred<List<Photo>>
}
