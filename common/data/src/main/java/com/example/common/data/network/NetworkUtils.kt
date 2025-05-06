package com.example.common.data.network

import android.util.Log
import com.example.common.domain.network.ResponseResult
import java.io.IOException
import java.net.ConnectException
import java.net.UnknownHostException


/**
 * Функция для обработки ошибок при выполнении сетевых запросов.
 * Обрабатывает исключения и возвращает результат запроса в виде [ResponseResult].
 *
 * @param response Функция, выполняющая сетевой запрос и возвращающая [ResponseResult].
 * @return Результат запроса, содержащий данные или информацию об ошибке.
 */
internal suspend fun <T> getResponseResultWrappedAllErrors(response: suspend () -> ResponseResult<T>): ResponseResult<T> =
    try {
        response()
    } catch (e: UnknownHostException) {
        Log.e("error", e.stackTraceToString())
        ResponseResult.Error(UnknownHostException("Нет подключения к интернету ${e.localizedMessage}"))
    } catch (e: ConnectException) {
        Log.e("error", e.stackTraceToString())
        ResponseResult.Error(ConnectException("Нет подключения к интернету ${e.localizedMessage}"))
    } catch (e: IOException) {
        Log.e("error", e.stackTraceToString())
        ResponseResult.Error(IOException("Ошибка сети: ${e.localizedMessage}"))
    } catch (e: Exception) {
        Log.e("error", e.stackTraceToString())
        ResponseResult.Error(Exception("Неизвестная ошибка: ${e.localizedMessage}"))
    }
