package com.example.common.domain.coroutines

import kotlinx.coroutines.CoroutineDispatcher

interface EditableDispatchers {
    val ioDispatcher: CoroutineDispatcher
}