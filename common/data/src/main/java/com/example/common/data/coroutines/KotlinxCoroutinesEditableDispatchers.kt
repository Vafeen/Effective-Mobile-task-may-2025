package com.example.common.data.coroutines

import com.example.common.domain.coroutines.EditableDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class KotlinxCoroutinesEditableDispatchers : EditableDispatchers {
    override val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
}