package com.twaun95.moviestar.data.di

import com.google.gson.GsonBuilder
import com.twaun95.moviestar.application.Logger
import com.twaun95.moviestar.data.service.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = "http://www.omdbapi.com/"
    private const val TIME_OUT_COUNT : Long = 30

    @Provides
    @Singleton
    fun provideClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor {
                it.proceed(
                    it.request()
                        .newBuilder()
                        .build()
                ).also { response ->
                    Logger.d("Response Code: ${response.code}")
                    Logger.d("Response IsSuccessFul: ${response.isSuccessful}")
                    Logger.d("Response Body: ${response.peekBody(4096).string()}")
                }
            }
            .connectTimeout(TIME_OUT_COUNT, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT_COUNT, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(
                GsonBuilder().serializeNulls().create()
            ))
            .client(client)
            .build()
    }

//    @Provides
//    @Singleton
//    fun getAPIKey(): APIKey {
//        return APIKey(key = BuildConfig.API_KEY)
//    }

    @Provides
    @Singleton
    fun provideMovieService(retrofit: Retrofit): MovieService {
        return retrofit.create(MovieService::class.java)
    }
}