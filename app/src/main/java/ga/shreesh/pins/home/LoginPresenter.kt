package ga.shreesh.pins.home

import ga.shreesh.pins.repository.IO
import ga.shreesh.pins.repository.LoginRepository
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.withContext

class LoginPresenter(private val loginRepository: LoginRepository, private val parentJob: Job) {

    fun login(authString: String, showToken: (String) -> Unit, showError: (String) -> Unit) = async(IO, parent = parentJob) {
        try {
            val token = loginRepository.fetchToken(authString).await().result
            if (token.isNotEmpty()) {
                withContext(UI) {
                    showToken(token)
                }
            } else {
                withContext(UI) {
                    showError("Could not get token")
                }
            }
        } catch (exception: Exception) {
            withContext(UI) {
                showError(exception.localizedMessage)
            }
        }
    }
}
