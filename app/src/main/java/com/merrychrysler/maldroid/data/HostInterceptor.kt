package com.merrychrysler.maldroid.data

import okhttp3.Interceptor
import okhttp3.Response

class HostInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        if (request.header("Api-Url") != null) {
            val newUrl =
                request.url.newBuilder().scheme("https").host("api.myanimelist.net").build()
            request = request.newBuilder().url(newUrl).build()
        }
        return chain.proceed(request)
    }
}