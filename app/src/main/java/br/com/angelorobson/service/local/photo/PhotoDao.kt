package br.com.angelorobson.service.local.photo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.angelorobson.domain.Photo
import io.reactivex.Completable
import io.reactivex.Maybe

@Dao
interface PhotoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(photos: List<Photo>): Completable

    @Query("SELECT * FROM Photo")
    fun getPhotos(): Maybe<List<Photo>>

    @Query("DELETE FROM Photo")
    fun removeAll(): Completable

}