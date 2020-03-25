package br.com.angelorobson.di

import androidx.room.Room
import br.com.angelorobson.service.local.AppDatabase
import org.koin.dsl.module

val daoModule = module {

    single { Room.databaseBuilder(get(), AppDatabase::class.java, "template_database").build() }

    single { get<AppDatabase>().photoDao() }

}