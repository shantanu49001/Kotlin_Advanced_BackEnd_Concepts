package com.lukaslechner.coroutineusecasesonandroid.mock

import com.lukaslechner.coroutineusecasesonandroid.utils.MockNetworkInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface MockApi {

    //pi functions that return response
    @GET("recent-android-versions")
    suspend fun getRecentAndroidVersions(): List<AndroidVersion>

    @GET("android-version-features/{apiLevel}")
    suspend fun getAndroidVersionFeatures(@Path("apiLevel") apiLevel: Int): VersionFeatures
}

//retofit builder of the mock api
fun createMockApi(interceptor: MockNetworkInterceptor): MockApi {
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("http://localhost/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(MockApi::class.java)
}