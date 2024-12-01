package com.geekforgeek.netfix_retrofit

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitHelper {

    @GET(".")
    suspend fun movieList(): Response<ArrayList<MovieModel>>

    companion object {
        private val BASE_URL = "https://imdb-top-100-movies.p.rapidapi.com/"

        val retrofitClient = OkHttpClient.Builder().apply {
            addInterceptor(
                Interceptor { chin ->
                    val build = chin.request().newBuilder()
                    build.header("x-rapidapi-key", "4cb24d0259msh736502921b24d65p164047jsn96daf4504eed")
                    build.header("x-rapidapi-host", "imdb-top-100-movies.p.rapidapi.com")
                    return@Interceptor chin.proceed(build.build())
                }
            )
        }.build()

        fun getInstance(): RetrofitHelper {
            val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(retrofitClient)
                .build()

            return retrofit.create(RetrofitHelper::class.java)
        }
    }
}