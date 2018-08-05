package ga.shreesh.pins.home

import ga.shreesh.pins.model.Post
import ga.shreesh.pins.repository.IO
import ga.shreesh.pins.repository.PostRepository
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.withContext

class HomePresenter(private val postRepository: PostRepository,
                    private val parentJob: Job = Job()) {
    fun fetchAllPosts(showPosts: (List<Post>) -> Unit, showError: (String) -> Unit) = launch(IO, parent = parentJob) {
        try {
            val request = postRepository.getAllPosts().await()
            withContext(UI) {
                if (request.isSuccessful) {
                    showPosts(request.body() ?: listOf())
                } else {
                    showError(request.errorBody()?.string() ?: "Network Error")
                }
            }
        } catch (exception: Exception) {
            withContext(UI) {
                showError(exception.localizedMessage)
            }

        }
    }

    fun fetchPopularPosts(showPosts: (List<Post>) -> Unit, showError: (String) -> Unit) = launch(IO, parent = parentJob) {
        try {
            val request = postRepository.getPopularPosts().await()
            withContext(UI) {
                if (request.isSuccessful) {
                    showPosts(request.body() ?: listOf())
                } else {
                    showError(request.errorBody()?.string() ?: "Network Error")
                }
            }
        } catch (exception: Exception) {
            withContext(UI) {
                showError(exception.localizedMessage)
            }

        }
    }

    fun fetchNetworkPosts(showPosts: (List<Post>) -> Unit, showError: (String) -> Unit) = launch(IO, parent = parentJob) {
        try {
            val request = postRepository.getNetworkPosts().await()
            withContext(UI) {
                if (request.isSuccessful) {
                    showPosts(request.body() ?: listOf())
                } else {
                    showError(request.errorBody()?.string() ?: "Network Error")
                }
            }
        } catch (exception: Exception) {
            withContext(UI) {
                showError(exception.localizedMessage)
            }

        }
    }

    fun destroy() {
        parentJob.cancel()
    }
}