package ga.shreesh.pins.repository

import ga.shreesh.pins.api.LoginApi
import retrofit2.Retrofit

class LoginRepository(private val retrofit: Retrofit) {
    private val loginApi: LoginApi by lazy {
        LoginApi.create(retrofit)
    }

    fun fetchToken(authString: String) = loginApi.token(authString)
}