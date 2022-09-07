package uz.behzod.eightytwenty.utils

import java.lang.Exception

// Result Wrapper <Left = Exception, Right = Value/Success>

sealed class Result<out E, out V> {
    object Loading : Result<Nothing, Nothing>()
    data class Success<out V>(val data: V) : Result<Nothing, V>()
    data class Failure<out E>(val exception: E) : Result<E, Nothing>()

    companion object Factory {

        inline fun <V> build(action: () -> V): Result<Exception, V> {
            return try {
                Success(action.invoke())
            } catch (e: Exception) {
                Failure(e)
            }
        }

        fun buildLoading(): Result<Exception,Nothing> {
            return Loading
        }
    }
}
