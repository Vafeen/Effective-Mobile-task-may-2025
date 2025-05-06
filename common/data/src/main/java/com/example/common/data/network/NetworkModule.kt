package com.example.common.data.network

import com.example.common.domain.network.CoursesRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


internal val NetworkModule = module {
    singleOf(::RetrofitCoursesRepository) {
        bind<CoursesRepository>()
    }
}
