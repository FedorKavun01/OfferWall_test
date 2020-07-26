package com.example.offerwall_test

import android.util.Log
import retrofit2.Response
import java.io.IOException

open class BaseRepository {

    val TAG = "mytag"

    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>, errorMessage: String): T? {
        val result = safeApiResult(call, errorMessage)
        var data: T? = null

        when(result) {
            is Result.Success -> {
                data = result.data
                Log.d(TAG, "safeApiCall: Success")
            }
            is Result.Error -> {
                Log.d(TAG, "safeApiCall: $errorMessage")
            }
        }

        return data
    }

    private suspend fun <T : Any> safeApiResult(call: suspend ()-> Response<T>, errorMessage: String): Result<T> {
        val response = call.invoke()
        Log.d(TAG, "safeApiResult: Start")
        if (response.isSuccessful) {
            return Result.Success(response.body()!!)
        }
        Log.d(TAG, "safeApiResult: Error")
        return Result.Error(IOException(errorMessage))
    }
}