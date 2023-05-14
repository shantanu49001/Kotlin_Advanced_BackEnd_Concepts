package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase1

import com.google.gson.Gson
import com.lukaslechner.coroutineusecasesonandroid.mock.createMockApi
import com.lukaslechner.coroutineusecasesonandroid.mock.mockAndroidVersions
import com.lukaslechner.coroutineusecasesonandroid.utils.MockNetworkInterceptor

fun mockApi() =
    createMockApi(//function is there in mock palkage--->sample api hai
        MockNetworkInterceptor()
            .mock(
                "http://localhost/recent-android-versions",
                { Gson().toJson(mockAndroidVersions) },//list of information hai just
                200,
                1500
            )
    )