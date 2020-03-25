package br.com.angelorobson.service.local

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.angelorobson.domain.Photo
import br.com.angelorobson.service.local.photo.PhotoDao

@Database(entities = [Photo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun photoDao(): PhotoDao
}