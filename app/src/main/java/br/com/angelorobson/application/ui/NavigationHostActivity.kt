package br.com.angelorobson.application.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import br.com.angelorobson.covid19.R
import kotlinx.android.synthetic.main.host_navigation_activity.*

class NavigationHostActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.host_navigation_activity)
        val navController = Navigation.findNavController(this, R.id.my_nav_fragment)
        val bottomNav = bottomNavigation

        bottomNav.setupWithNavController(navController)
    }
}
