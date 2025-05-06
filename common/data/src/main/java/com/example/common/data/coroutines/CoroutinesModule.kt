package com.example.common.data.coroutines

import com.example.common.domain.coroutines.EditableDispatchers
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module


internal val CoroutinesModule = module {
    factoryOf(::KotlinxCoroutinesEditableDispatchers) {
        bind<EditableDispatchers>()
    }
}