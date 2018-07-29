package ga.shreesh.pins.api

import ga.shreesh.pins.model.TokenResponse
import kotlinx.coroutines.experimental.Deferred
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface LoginApi {
    @GET("user/api_token")
    fun token(@Header("Authorization") authToken: String, @Query("format") format: String = "json"): Deferred<TokenResponse>

    companion object {
        fun create(retrofit: Retrofit) = retrofit.create(LoginApi::class.java)!!
    }
}