package com.example.android.databinding.basicsample.data.remote.response.error


open class ApiError(
        val code: Int = -1,
        var message: String? ="",
        var logMessage: String
) {

    companion object {
        fun badResponse(logMessage: String) = ApiError(502, "error in getting response ", logMessage)
        fun noConnection(logMessage: String) = ApiError(404, "error in connecting to repository", logMessage)
        fun timeOut(logMessage: String) = ApiError(408, " Time out  error", logMessage)
        fun emptyResponse(logMessage: String) = ApiError(204, "no data available in repository", logMessage)
        fun notDefined(logMessage: String) = ApiError(404, "an unexpected error", logMessage)
        fun badRequest(logMessage: String) = ApiError(401, "Bad Request", logMessage)
    }
}


