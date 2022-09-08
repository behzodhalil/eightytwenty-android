package uz.behzod.eightytwenty.utils

import java.lang.Exception

// Result Wrapper <Left = Exception, Right = Value/Success>

sealed class Resource<out E, out V> {
    object Loading : Resource<Nothing, Nothing>()
    data class Success<out V>(val data: V) : Resource<Nothing, V>()
    data class Failure<out E>(val exception: E) : Resource<E, Nothing>()

    companion object Factory {

        inline fun <V> build(action: () -> V): Resource<Exception, V> {
            return try {
                Success(action.invoke())
            } catch (e: Exception) {
                Failure(e)
            }
        }

        fun buildLoading(): Resource<Exception,Nothing> {
            return Loading
        }
    }
}
