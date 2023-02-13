package com.anil.groceries.network

import android.content.Context
import com.anil.groceries.BuildConfig
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val NETWORK_TIMEOUT = 30L
const val BASE_URL = "https://jsonplaceholder.typicode.com/"

class Api constructor(private val context: Context) {

    val apiService: ApiService by lazy {
        retrofit().create(ApiService::class.java)
    }

    private fun retrofit(): Retrofit {
        return Retrofit.Builder().addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okhttpClient()).addConverterFactory(NullOnEmptyConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory()).baseUrl(BASE_URL).build()
    }

    private fun okhttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        else interceptor.setLevel(HttpLoggingInterceptor.Level.NONE)

        return OkHttpClient.Builder().readTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS).addInterceptor(interceptor)
            .addInterceptor(Interceptor { chain: Interceptor.Chain ->
                val request: Request = chain.request().newBuilder()
                    //.addHeader("X-Wise-Package-Name", context.packageName)
                    //.addHeader("X-Wise-Platform", "android")
                    .build()

                //Log.d(LOG_TAG, "Headers ${request.headers}")

                chain.proceed(request)
            }).build()
    }

    companion object : SingletonHolder<Api, Context>(::Api)
}