package ga.shreesh.pins.api

import ga.shreesh.pins.model.Post
import kotlinx.coroutines.experimental.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface PostsApi {
    @GET("posts/all")
    fun posts(@Query("auth_token", encoded = true) token: String,
              @Query("tag") tag: String = "",
              @Query("dt") date: String = "",
              @Query("url") url: String = "",
              @Query("meta") metatag: String = "",
              @Query("format") format: String = "json"): Deferred<Response<List<Post>>>


    companion object {
        fun create(retrofit: Retrofit) = retrofit.create(PostsApi::class.java)!!
    }
}