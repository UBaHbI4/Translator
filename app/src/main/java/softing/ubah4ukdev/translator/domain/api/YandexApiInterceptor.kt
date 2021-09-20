package softing.ubah4ukdev.translator.domain.api

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response
import softing.ubah4ukdev.translator.BuildConfig

/**
 *   Project: Translator
 *
 *   Package: softing.ubah4ukdev.translator.domain.datasource
 *
 *   Created by Ivan Sheynmaer
 *
 *   Description:
 *   Interceptor для ретрофита
 *
 *   2021.09.19
 *
 *   v1.0
 */
object YandexApiInterceptor : Interceptor {
    private var responseCode: Int = 0

    private const val USER_NAME = "demo"
    private const val HEADER_NAME = "Authorization"

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(
            chain.request()
                .newBuilder()
                .header(
                    HEADER_NAME,
                    Credentials.basic(USER_NAME, BuildConfig.API_TOKEN)
                )
                .build()
        )
        responseCode = response.code
        return response
    }

    fun getResponseCode(): ServerResponseStatusCode {
        var statusCode = ServerResponseStatusCode.UNDEFINED_ERROR
        when (responseCode / 100) {
            1 -> statusCode = ServerResponseStatusCode.INFO
            2 -> statusCode = ServerResponseStatusCode.SUCCESS
            3 -> statusCode = ServerResponseStatusCode.REDIRECTION
            4 -> statusCode = ServerResponseStatusCode.CLIENT_ERROR
            5 -> statusCode = ServerResponseStatusCode.SERVER_ERROR
        }
        return statusCode
    }

    enum class ServerResponseStatusCode {
        INFO,
        SUCCESS,
        REDIRECTION,
        CLIENT_ERROR,
        SERVER_ERROR,
        UNDEFINED_ERROR
    }
}