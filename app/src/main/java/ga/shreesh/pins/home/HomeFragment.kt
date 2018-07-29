package ga.shreesh.pins.home

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ga.shreesh.pins.R
import ga.shreesh.pins.navigation.NavigationManager
import ga.shreesh.pins.repository.PostRepository
import ga.shreesh.pins.repository.retrofit
import ga.shreesh.pins.repository.sharedPreferencesFile

class HomeFragment : Fragment() {
    private lateinit var homePresenter: HomePresenter
    private lateinit var navigationManager: NavigationManager

    companion object {
        @JvmStatic
        fun create(): Fragment = HomeFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreferences = context!!.getSharedPreferences(sharedPreferencesFile, Context.MODE_PRIVATE)
        if (!sharedPreferences.contains("KEY_TOKEN")) {
            navigationManager.navigate(NavigationManager.Screen.LOGIN)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        navigationManager = (context as HomeActivity).navigationManager
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        homePresenter = HomePresenter(PostRepository(retrofit, "shreeshga:4bc5a64fe7ec86493ad8"))
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        homePresenter.fetchAllPosts(
                showPosts = {
                    Log.d("", "Posts loaded of size ${it.size}")

                }, showError = {
            Log.e("Error : %s", it)
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        homePresenter.destroy()
    }
}
