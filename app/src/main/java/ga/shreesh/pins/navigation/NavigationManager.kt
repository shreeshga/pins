package ga.shreesh.pins.navigation

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import ga.shreesh.pins.R
import ga.shreesh.pins.home.HomeFragment
import ga.shreesh.pins.home.LoginFragment


class NavigationManager(private val fragmentManager: FragmentManager) {
    enum class Screen {
        LOGIN,
        HOME
    }

    fun navigate(screen: Screen) =
            when (screen) {
                Screen.LOGIN -> pushFragment(LoginFragment.create())
                Screen.HOME -> pushFragment(HomeFragment.create())
            }


    private fun pushFragment(fragment: Fragment) {
        fragmentManager
                .beginTransaction()
                .replace(R.id.fragment, fragment)
                .commit()
    }
}