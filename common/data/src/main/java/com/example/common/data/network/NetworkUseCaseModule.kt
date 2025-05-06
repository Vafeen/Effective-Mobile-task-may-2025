package com.example.common.data.network

import com.example.common.domain.usecase.GetAllCoursesUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal val NetworkUseCaseModule = module {
    factoryOf(::GetAllCoursesUseCase)
}