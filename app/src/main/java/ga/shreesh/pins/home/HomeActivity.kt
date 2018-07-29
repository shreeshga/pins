package ga.shreesh.pins.home

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ga.shreesh.pins.R
import ga.shreesh.pins.navigation.NavigationManager
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    lateinit var navigationManager: NavigationManager
    private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
        navigationManager = NavigationManager(supportFragmentManager)
        navigationManager.navigate(NavigationManager.Screen.HOME)
    }

}
