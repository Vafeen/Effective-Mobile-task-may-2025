package com.example.common.data.local_database

import androidx.room.Room
import com.example.common.domain.local_database.CoursesLocalRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal val DatabaseModule = module {
    factoryOf(::RoomCoursesLocalRepository) {
        bind<CoursesLocalRepository>()
    }
    single<AppDatabase> {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            AppDatabase.NAME
        ).build()
    }

}