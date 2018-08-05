package ga.shreesh.pins.repository

import ga.shreesh.pins.api.PostsApi
import retrofit2.Retrofit

class PostRepository(private val retrofit: Retrofit, private val token: String) {
    private val postsApi: PostsApi by lazy {
        PostsApi.create(retrofit)
    }

    fun getAllPosts() = postsApi.posts(token)

    fun getPopularPosts() = postsApi.postsPopular(token)

    fun getNetworkPosts() = postsApi.postsNetwork((token))
}