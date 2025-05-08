package com.example.common.domain.coroutines

import kotlinx.coroutines.CoroutineDispatcher

/**
 * Интерфейс, предоставляющий диспатчеры корутин, которые могут быть изменены или заменены.
 */
interface EditableDispatchers {
    val ioDispatcher: CoroutineDispatcher
}