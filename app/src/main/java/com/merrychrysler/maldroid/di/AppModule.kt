package com.merrychrysler.maldroid.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.merrychrysler.maldroid.BuildConfig
import com.merrychrysler.maldroid.data.AuthInterceptor
import com.merrychrysler.maldroid.data.MalApi
import com.merrychrysler.maldroid.data.repository.MalRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideMalApi(httpClient: OkHttpClient): MalApi {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URl).client(httpClient)
            .addConverterFactory(
                Json.asConverterFactory(contentType)
            ).build().create(MalApi::class.java)
    }

    @Provides
    @Singleton
    fun provideHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor
    ): OkHttpClient {
        return OkHttpClient().newBuilder().apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(loggingInterceptor)
            }
            addInterceptor(authInterceptor)
        }.build()
    }

    @Provides
    @Singleton
    fun provideAuthInterceptor(): AuthInterceptor {
        return AuthInterceptor()
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideMalRepository(api: MalApi): MalRepositoryImpl {
        return MalRepositoryImpl(api)
    }
}