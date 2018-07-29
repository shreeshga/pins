package ga.shreesh.pins.home

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.util.Base64.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ga.shreesh.pins.R
import ga.shreesh.pins.navigation.NavigationManager
import ga.shreesh.pins.repository.LoginRepository
import ga.shreesh.pins.repository.retrofit
import ga.shreesh.pins.repository.sharedPreferencesFile
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.coroutines.experimental.Job

class LoginFragment : Fragment() {
    private lateinit var loginPresenter: LoginPresenter
    private lateinit var navigationManager: NavigationManager
    private val fragmentJob = Job()

    companion object {
        @JvmStatic
        fun create() = LoginFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        loginPresenter = LoginPresenter(LoginRepository(retrofit), fragmentJob)
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        navigationManager = (context as HomeActivity).navigationManager
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        login.setOnClickListener {
            login.text = "Signing In..."
            login.isEnabled = false

            loginPresenter.login("Basic " + encodeToString("${username.text}:${password.text}".toByteArray(), NO_WRAP or URL_SAFE),
                    showToken = { token ->
                        context?.let {
                            val sharedPreferences = it.getSharedPreferences(sharedPreferencesFile, Context.MODE_PRIVATE)
                            sharedPreferences.edit().putString("KEY_TOKEN", token).apply()
                            navigationManager.navigate(NavigationManager.Screen.HOME)
                        }

                    },
                    showError = { error ->
                        container?.let {
                            login.text = "Sign In"
                            login.isEnabled = true
                            Snackbar.make(it, error, Snackbar.LENGTH_SHORT).show()
                        }
                    })
        }
    }
}