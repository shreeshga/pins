package ga.shreesh.pins.repository

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import kotlinx.coroutines.experimental.CloseableCoroutineDispatcher
import kotlinx.coroutines.experimental.asCoroutineDispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors

const val sharedPreferencesFile = "PinsPreferences"

val IO: CloseableCoroutineDispatcher = Executors.newFixedThreadPool(100).asCoroutineDispatcher()

val retrofit: Retrofit = Retrofit.Builder()
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://api.pinboard.in/v1/")
        .client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS)).build())
        .build()