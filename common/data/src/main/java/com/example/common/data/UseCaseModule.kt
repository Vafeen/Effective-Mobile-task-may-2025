package com.example.common.data

import com.example.common.domain.usecase.FetchDataAndUpdateDbUseCase
import com.example.common.domain.usecase.GetAllCoursesFromLocalDatabaseUseCase
import com.example.common.domain.usecase.InsertCoursesInLocalDatabaseUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal val UseCaseModule = module {
    factoryOf(::FetchDataAndUpdateDbUseCase)
    factoryOf(::GetAllCoursesFromLocalDatabaseUseCase)
    factoryOf(::InsertCoursesInLocalDatabaseUseCase)
}