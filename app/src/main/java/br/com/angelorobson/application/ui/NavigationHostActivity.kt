package br.com.angelorobson.application.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import br.com.angelorobson.R

class NavigationHostActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.host_navigation_activity)

        Navigation.findNavController(this, R.id.my_nav_fragment)
    }
}
