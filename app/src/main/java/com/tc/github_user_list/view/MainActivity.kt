package com.tc.github_user_list.view

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.tc.github_user_list.R
import com.tc.github_user_list.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val navHost =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        navController = navHost.findNavController()

        setupActionBarWithNavController(navController)
        navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_users -> {
                    navView.visibility = View.VISIBLE
                }

                R.id.navigation_search -> {
                    navView.visibility = View.VISIBLE
                }

                else -> {
                    navView.visibility = View.GONE
                }
            }

        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.navigation_users -> super.onOptionsItemSelected(item)
            R.id.navigation_search -> super.onOptionsItemSelected(item)
            else -> navController.navigateUp() || super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {

        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}