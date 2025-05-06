package com.example.common.data.services.courses_service

import com.example.common.data.data_models.courses_service.CoursesResponse
import retrofit2.Response
import retrofit2.http.GET

/**
 * Сервис для получения данных о курсах из JSON-файла, размещенного на Google Drive.
 * Использует Retrofit для выполнения HTTP-запроса и получения структурированных данных.
 *
 * @see <a href="https://drive.google.com/uc?export=download&id=15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q">Файл с курсами</a>
 */
internal interface RetrofitCoursesService {
    /**
     * Загружает список курсов из удаленного JSON-файла.
     *
     * @return Ответ сервера в виде [Response], содержащий распарсенный объект [CoursesResponse].
     */
    @GET(COURSES_URL)
    suspend fun getAll(): Response<CoursesResponse>

    companion object {
        const val BASE_URL = "https://drive.google.com"

        /**
         * Прямая ссылка для скачивания JSON-файла с курсами.
         */
        private const val COURSES_URL =
            "$BASE_URL/uc?export=download&id=15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q&confirm=1"
    }
}