package com.example.common.domain.usecase

import com.example.common.domain.local_database.CoursesLocalRepository
import com.example.common.domain.models.Course
import com.example.common.domain.network.CoursesNetworkRepository
import com.example.common.domain.network.ResponseResult
import com.example.common.domain.utils.cleverUpdatingCourses
import kotlinx.coroutines.flow.first

/**
 * Use case для получения списка курсов с сети и обновления локальной базы данных.
 *
 * Выполняет загрузку курсов из сетевого репозитория, сравнивает с локальными курсами
 * и обновляет локальное хранилище, вызывая операции вставки и удаления.
 *
 * @property networkRepository Репозиторий для получения курсов из сети.
 * @property localRepository Репозиторий для работы с локальной базой данных курсов.
 */
class FetchDataAndUpdateDbUseCase(
    private val networkRepository: CoursesNetworkRepository,
    private val localRepository: CoursesLocalRepository
) {
    /**
     * Выполняет загрузку курсов из сети и обновляет локальную базу данных.
     *
     * @return [ResponseResult]<[Any]> - результат операции.
     *
     * Тип `ResponseResult<Any>` используется здесь
     * исключительно для отображения результата на интерфейсе.
     * Фактически возвращается либо ошибка, либо успешный результат,
     * Чтобы была имитация работы только с подключением к сети,
     * иначе данные могут быть не актуальными
     */
    suspend operator fun invoke(): ResponseResult<Any> =
        when (val result = networkRepository.getAllCourses()) {
            is ResponseResult.Error -> result
            is ResponseResult.Success<List<Course>> -> {
                val lastCourses = localRepository.getAll().first()
                cleverUpdatingCourses(
                    lastCourses = lastCourses,
                    newCourses = result.data,
                    insert = localRepository::insert,
                    delete = localRepository::delete,
                )
                ResponseResult.Success(result.data)
            }
        }
}