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
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    lateinit var homePresenter: HomePresenter
        private set
    lateinit var navigationManager: NavigationManager
        private set

    companion object {
        @JvmStatic
        fun create(): Fragment = HomeFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        context?.getSharedPreferences(sharedPreferencesFile, Context.MODE_PRIVATE)?.let {
            if (!it.contains("KEY_TOKEN")) {
                navigationManager.navigate(NavigationManager.Screen.LOGIN)
            }
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        navigationManager = (context as HomeActivity).navigationManager
    }

    override fun onAttachFragment(childFragment: Fragment?) {
        super.onAttachFragment(childFragment)
        (childFragment as ChildFragment).setPresenter(homePresenter)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        context?.getSharedPreferences(sharedPreferencesFile, Context.MODE_PRIVATE)
                ?.let {
                    homePresenter =
                            HomePresenter(PostRepository(retrofit, it.getString("KEY_TOKEN", "")))
                }

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fetch()

        bottomNavigationBar.setOnNavigationItemSelectedListener {
            addFragment(when (it.itemId) {
                R.id.tab_all -> AllPostsFragment.create()
                R.id.tab_popular -> PopularPostsFragment.create()
                R.id.tab_network -> NetworkPostsFragment.create()
                else -> throw IllegalArgumentException()
            })
        }
    }

    private fun addFragment(fragment: Fragment): Boolean {
        fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainer, fragment)?.commit()
        return true
    }

    private fun fetch() {
        homePresenter.fetchAllPosts(
                showPosts = {
                    Log.d("", "Posts loaded of size ${it.size}")
                },
                showError = {
                    Log.e("Error : %s", it)
                })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        homePresenter.destroy()
    }
}
