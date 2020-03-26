package com.example.android.databinding.basicsample.data.remote.response.error

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import io.reactivex.observers.DisposableObserver
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ApiDisposable<T>(private val success: (body: T) -> Unit,
                       private val failure: (error: ApiError) -> Unit = {}) : DisposableObserver<T>() {

    private val TAG = ApiDisposable::class.java.simpleName

    override fun onComplete() {
        TODO("Not yet implemented")
    }

    override fun onNext(t: T) {
        if (t == null || (t is List<*> && t.isEmpty()))
            failure(ApiError.emptyResponse("Empty Data"))
        else
            success(t)
    }

    override fun onError(e: Throwable) {
        Log.d(TAG, "OnError : $e")
        when (e) {
            is HttpException -> {
                val errorMessage = getErrorMessage(e.response()?.errorBody())
                failure(ApiError.badResponse(errorMessage))
            }
            is SocketTimeoutException -> {
                failure(ApiError.timeOut(e.localizedMessage))
            }
            is IOException -> {
                failure(ApiError.noConnection(e.localizedMessage))
            }
            is NullPointerException -> {
                failure(ApiError.emptyResponse(e.localizedMessage))
            }

            else -> failure(ApiError.notDefined(e.localizedMessage))
        }
    }

    private fun getErrorMessage(errorBody: ResponseBody?): String = try {
        val result = errorBody?.string()
        Log.d(TAG, "getErrorMessage() called with: errorBody = [$result]")
        val json = Gson().fromJson(result, JsonObject::class.java)
        json.toString()
    } catch (t: Throwable) {
        ""
    }
}